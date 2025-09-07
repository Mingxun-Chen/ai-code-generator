package me.cmx.aicodegenerator.core;

import cn.hutool.json.JSONUtil;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.tool.ToolExecution;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import me.cmx.aicodegenerator.ai.AiCodeGenerateService;
import me.cmx.aicodegenerator.ai.AiCodeGenerateServiceFactory;
import me.cmx.aicodegenerator.ai.model.HtmlCodeResult;
import me.cmx.aicodegenerator.ai.model.MultiFileCodeResult;
import me.cmx.aicodegenerator.ai.model.message.AiResponseMessage;
import me.cmx.aicodegenerator.ai.model.message.ToolExecutedMessage;
import me.cmx.aicodegenerator.ai.model.message.ToolRequestMessage;
import me.cmx.aicodegenerator.constant.AppConstant;
import me.cmx.aicodegenerator.core.builder.VueProjectBuilder;
import me.cmx.aicodegenerator.core.parser.CodeParserExecutor;
import me.cmx.aicodegenerator.core.saver.CodeFileSaverExecutor;
import me.cmx.aicodegenerator.exception.BusinessException;
import me.cmx.aicodegenerator.exception.ErrorCode;
import me.cmx.aicodegenerator.model.enums.CodeGenTypeEnum;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.File;


@Slf4j
@Service
public class AiCodeGeneratorFacade {

    @Resource
    private AiCodeGenerateServiceFactory aiCodeGenerateServiceFactory;

    @Resource
    private VueProjectBuilder vueProjectBuilder;

    /**
     * 统一入口: 根据类型生成并保存代码
     *
     * @param userMessage 用户提示词
     * @param codeGenType 代码生成类型
     * @param appId
     * @return
     */
    public File generateAndSaveCode(String userMessage, CodeGenTypeEnum codeGenType, Long appId) {
        if (codeGenType == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "生成类型为空");
        }
        // 根据 appId 获取 一个 AI.Service 服务
        AiCodeGenerateService aiCodeGenerateService = aiCodeGenerateServiceFactory.getAiCodeGenerateService(appId, codeGenType);
        return switch (codeGenType) {
            case HTML -> {
                HtmlCodeResult htmlCodeResult = aiCodeGenerateService.generateHtmlCode(userMessage);
                yield CodeFileSaverExecutor.executeSaver(htmlCodeResult, codeGenType, appId);
            }
            case MULTI_FILE -> {
                MultiFileCodeResult multiFileCodeResult = aiCodeGenerateService.generateMultiFileCode(userMessage);
                yield CodeFileSaverExecutor.executeSaver(multiFileCodeResult, codeGenType, appId);
            }
            default -> {
                String errorMessage = "生成的类型不支持：" + codeGenType.getValue();
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, errorMessage);
            }
        };
    }

    /**
     * 统一入口: 根据类型生成并保存代码（流式）
     *
     * @param userMessage 用户提示词
     * @param codeGenType 代码生成类型
     * @param appId
     * @return
     */
    public Flux<String> generateAndSaveCodeStream(String userMessage, CodeGenTypeEnum codeGenType, Long appId) {
        if (codeGenType == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "生成类型为空");
        }
        AiCodeGenerateService aiCodeGenerateService = aiCodeGenerateServiceFactory.getAiCodeGenerateService(appId, codeGenType);
        return switch (codeGenType) {
            case HTML -> {
                Flux<String> codeStream = aiCodeGenerateService.generateHtmlCodeStream(userMessage);
                yield processCodeStream(codeStream, codeGenType, appId);
            }
            case MULTI_FILE -> {
                Flux<String> codeStream = aiCodeGenerateService.generateMultiFileCodeStream(userMessage);
                yield processCodeStream(codeStream, codeGenType, appId);
            }
            case VUE_PROJECT -> {
                TokenStream tokenStream = aiCodeGenerateService.generateVueProjectCodeStream(appId, userMessage);
                yield processTokenStream(tokenStream, appId);
            }
            default -> {
                String errorMessage = "生成的类型不支持：" + codeGenType.getValue();
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, errorMessage);
            }
        };
    }


    public Flux<String> processCodeStream(Flux<String> codeStream, CodeGenTypeEnum codeGenType, Long appId) {
        StringBuilder codeBuilder = new StringBuilder();
        return codeStream.doOnNext(chunk -> {
            codeBuilder.append(chunk);
        }).doOnComplete(() -> {
            try {
                String completeCode = codeBuilder.toString();
                Object parseResult = CodeParserExecutor.executeParser(completeCode, codeGenType);
                File savePath = CodeFileSaverExecutor.executeSaver(parseResult, codeGenType, appId);
                log.info("保存成功，路径为：" + savePath.getAbsolutePath());
            } catch (Exception e) {
                log.error("保存失败：{}", e.getMessage());
            }
        });
    }

    /**
     * 将 TokenStream 转换为 Flux<String>，并传递工具调用信息
     *
     * @param tokenStream TokenStream 对象
     * @return Flux<String> 流式响应
     */
    private Flux<String> processTokenStream(TokenStream tokenStream, Long appId) {
        return Flux.create(sink -> {
            tokenStream.onPartialResponse((String partialResponse) -> {
                        AiResponseMessage aiResponseMessage = new AiResponseMessage(partialResponse);
                        sink.next(JSONUtil.toJsonStr(aiResponseMessage));
                    })
                    .onPartialToolExecutionRequest((index, toolExecutionRequest) -> {
                        ToolRequestMessage toolRequestMessage = new ToolRequestMessage(toolExecutionRequest);
                        sink.next(JSONUtil.toJsonStr(toolRequestMessage));
                    })
                    .onToolExecuted((ToolExecution toolExecution) -> {
                        ToolExecutedMessage toolExecutedMessage = new ToolExecutedMessage(toolExecution);
                        sink.next(JSONUtil.toJsonStr(toolExecutedMessage));
                    })
                    .onCompleteResponse((ChatResponse response) -> {
                        // 构造 Vue 项目
                        String projectPath = AppConstant.CODE_OUTPUT_ROOT_DIR + "/vue_project_" + appId;
                        vueProjectBuilder.buildProject(projectPath);
                        sink.complete();
                    })
                    .onError((Throwable error) -> {
                        error.printStackTrace();
                        sink.error(error);
                    })
                    .start();
        });
    }


    ///**
    // * 生成并保存 html 代码
    // * @param userMessage 用户提示词
    // * @return
    // */
    //@Deprecated
    //private File generateAndSaveHtmlCode(String userMessage) {
    //    HtmlCodeResult htmlCodeResult = aiCodeGenerateService.generateHtmlCode(userMessage);
    //    return CodeFileSaver.saveHtmlCodeResult(htmlCodeResult);
    //}
    //
    ///**
    // * 生成并保存 多文件 代码
    // * @param userMessage 用户提示词
    // * @return
    // */
    //@Deprecated
    //private File generateAndSaveMultiFileCode(String userMessage) {
    //    MultiFileCodeResult multiFileCodeResult = aiCodeGenerateService.generateMultiFileCode(userMessage);
    //    return CodeFileSaver.saveMultiFileCodeResult(multiFileCodeResult);
    //}
    //
    ///**
    // * 生成并保存 html 代码（流式）
    // * @param userMessage 用户提示词
    // * @return
    // */
    //@Deprecated
    //private Flux<String> generateAndSaveHtmlCodeStream(String userMessage) {
    //    Flux<String> result = aiCodeGenerateService.generateHtmlCodeStream(userMessage);
    //    StringBuilder codeBuilder = new StringBuilder();
    //    return result.doOnNext(chunk -> {
    //        codeBuilder.append(chunk);
    //    }).doOnComplete(() -> {
    //        try{
    //            String completeString = codeBuilder.toString();
    //            HtmlCodeResult htmlCodeResult = CodeParser.parseHtmlCode(completeString);
    //            File saveDir = CodeFileSaver.saveHtmlCodeResult(htmlCodeResult);
    //            log.info("保存成功，路径为：" + saveDir.getAbsolutePath());
    //        } catch (Exception e) {
    //            log.error("保存失败，{}", e.getMessage());
    //        }
    //    });
    //}
    //
    ///**
    // * 生成并保存 多文件 代码（流式）
    // * @param userMessage 用户提示词
    // * @return
    // */
    //@Deprecated
    //private Flux<String> generateAndSaveMultiFileCodeStream(String userMessage) {
    //    Flux<String> result = aiCodeGenerateService.generateMultiFileCodeStream(userMessage);
    //    StringBuilder codeBuilder = new StringBuilder();
    //    return result.doOnNext(chunk -> {
    //        codeBuilder.append(chunk);
    //    }).doOnComplete(() -> {
    //       try {
    //           String completeString = codeBuilder.toString();
    //           MultiFileCodeResult multiFileCodeResult = CodeParser.parseMultiFileCode(completeString);
    //           File saveDir = CodeFileSaver.saveMultiFileCodeResult(multiFileCodeResult);
    //           log.info("保存成功，路径为：" + saveDir.getAbsolutePath());
    //       } catch (Exception e) {
    //           log.error("保存失败，{}",  e.getMessage());
    //       }
    //    });
    //}
}
