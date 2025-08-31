package me.cmx.aicodegenerator.ai;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiCodeGenerateServiceFactory {

    @Resource
    private ChatModel chatModel;

    @Resource
    private StreamingChatModel streamingChatModel;

    /**
     * 使用代理机制 创建一个接口实现类 规定了 方法的返回值和形参 传入prompt 返回result
     * @return
     */
    @Bean
    public AiCodeGenerateService aiCodeGenerateService(){
        return AiServices.builder(AiCodeGenerateService.class)
                .chatModel(chatModel)
                .streamingChatModel(streamingChatModel)
                .build();
    }
}
