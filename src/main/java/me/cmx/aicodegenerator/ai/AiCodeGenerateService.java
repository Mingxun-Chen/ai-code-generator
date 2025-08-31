package me.cmx.aicodegenerator.ai;

import dev.langchain4j.service.SystemMessage;
import me.cmx.aicodegenerator.ai.model.HtmlCodeResult;
import me.cmx.aicodegenerator.ai.model.MultiFileCodeResult;
import reactor.core.publisher.Flux;

public interface AiCodeGenerateService {

    /**
     * 生成 html 代码
     * @param userMessage 用户提示词
     * @return 生成的 html 代码结果
     */
    @SystemMessage(fromResource = "prompt/generate-html-system-prompt.txt")
    HtmlCodeResult generateHtmlCode (String userMessage);

    /**
     * 生成多文件代码
     * @param userMessage 用户提示词
     * @return 生成的多文件代码结果
     */
    @SystemMessage(fromResource = "prompt/generate-multi-files-system-prompt.txt")
    MultiFileCodeResult generateMultiFileCode(String userMessage);


    /**
     * 生成 html 代码（流式）
     * @param userMessage 用户提示词
     * @return 生成的 html 代码结果
     */
    @SystemMessage(fromResource = "prompt/generate-html-system-prompt.txt")
    Flux<String> generateHtmlCodeStream (String userMessage);

    /**
     * 生成多文件代码（流式）
     * @param userMessage 用户提示词
     * @return 生成的多文件代码结果
     */
    @SystemMessage(fromResource = "prompt/generate-multi-files-system-prompt.txt")
    Flux<String> generateMultiFileCodeStream(String userMessage);
}
