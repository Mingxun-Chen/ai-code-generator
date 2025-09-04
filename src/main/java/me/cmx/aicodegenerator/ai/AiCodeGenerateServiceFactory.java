package me.cmx.aicodegenerator.ai;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import dev.langchain4j.community.store.memory.chat.redis.RedisChatMemoryStore;
import dev.langchain4j.data.message.ToolExecutionResultMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import me.cmx.aicodegenerator.ai.tools.FileWriteTool;
import me.cmx.aicodegenerator.exception.BusinessException;
import me.cmx.aicodegenerator.exception.ErrorCode;
import me.cmx.aicodegenerator.model.enums.CodeGenTypeEnum;
import me.cmx.aicodegenerator.service.ChatHistoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Slf4j
@Configuration
public class AiCodeGenerateServiceFactory {

    @Resource
    private ChatModel chatModel;
    @Resource
    private StreamingChatModel openAiStreamingChatModel;
    @Resource
    private StreamingChatModel reasoningStreamingChatModel;
    @Resource
    private RedisChatMemoryStore redisChatMemoryStore;
    @Resource
    private ChatHistoryService chatHistoryService;

    /**
     * AI 服务实例缓存
     * 缓存策略:
     *  - 最大缓存 1000 个实例
     *  - 写入后 30 分钟后过期
     *  - 访问后 10 分钟过期
     */
    private final Cache<String, AiCodeGenerateService> serviceCache = Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(Duration.ofMinutes(30))
            .expireAfterAccess(Duration.ofMinutes(10))
            .removalListener((key, value, cause) -> {
                log.debug("AI 服务实例被移除，缓存键：{}, 原因：{}", key, cause);
            })
            .build();

    /**
     * 根据 appId 获取服务 (带缓存)
     * @param appId 应用 ID
     * @param codeGenType 代码生成类型
     * @return
     */
    public AiCodeGenerateService getAiCodeGenerateService(long appId, CodeGenTypeEnum codeGenType) {
        String cacheKey = buildCacheKey(appId, codeGenType);
        return serviceCache.get(cacheKey, key -> createAiCodeGenerateService(appId, codeGenType));
    }

    private String buildCacheKey(long appId, CodeGenTypeEnum codeGenType) {
        return appId + "_" + codeGenType.getValue();
    }


    /**
     * 创建新的 AI 服务实例
     */
    private AiCodeGenerateService createAiCodeGenerateService(long appId, CodeGenTypeEnum codeGenType){
        log.info("为appId：{}创建新的服务实例", appId);
        // 根据 appId 构建独立的对话记忆
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory
                .builder()
                .id(appId)
                .chatMemoryStore(redisChatMemoryStore)
                .maxMessages(20)
                .build();
        // 从数据库加载历史对话到记忆中
        chatHistoryService.loadChatHistoryToMemory(appId, chatMemory, 20);
        // 根据代码类型生成不同的模型配置
        return switch (codeGenType) {
            case VUE_PROJECT -> AiServices.builder(AiCodeGenerateService.class)
                    .streamingChatModel(reasoningStreamingChatModel)
                    .chatMemoryProvider(memoryId -> chatMemory)
                    .tools(new FileWriteTool())
                    .hallucinatedToolNameStrategy(toolExecutionRequest -> ToolExecutionResultMessage.from(
                            toolExecutionRequest, "Error, there is no tool called " + toolExecutionRequest.name()
                    ))
                    .build();
            // HTML MUTI_FILE 使用简单的生成模型
            case HTML, MULTI_FILE -> AiServices.builder(AiCodeGenerateService.class)
                    .chatModel(chatModel)
                    .streamingChatModel(openAiStreamingChatModel)
                    .chatMemory(chatMemory)
                    .build();
            default -> throw new BusinessException(ErrorCode.SYSTEM_ERROR, "不支持的代码生成类型:" + codeGenType.getValue());
        };
    }

    /**
     * 根据 appId 获取服务 (带缓存 兼容以前的逻辑)
     * @param appId 应用 ID
     * @return
     */
    public AiCodeGenerateService getAiCodeGenerateService(long appId) {
        return getAiCodeGenerateService(appId, CodeGenTypeEnum.HTML);
    }

    /**
     * 使用代理机制 创建一个接口实现类 规定了 方法的返回值和形参 传入prompt 返回result
     * @return
     */
    @Bean
    public AiCodeGenerateService aiCodeGenerateService(){
        //interface Assistant  {
        //    String chat(@MemoryId int memoryId, @UserMessage String message);
        //}
        //
        //Assistant assistant = AiServices.builder(Assistant.class)
        //        .chatModel(model)
        //        .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
        //        .build();
        //
        //Assistant assistant = AiServices.builder(Assistant.class)
        //        .chatModel(model)
        //        .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
        //        .build();
        return getAiCodeGenerateService(0L);
    }
}
