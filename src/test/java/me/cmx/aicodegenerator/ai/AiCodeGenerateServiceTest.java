package me.cmx.aicodegenerator.ai;

import jakarta.annotation.Resource;
import me.cmx.aicodegenerator.ai.model.HtmlCodeResult;
import me.cmx.aicodegenerator.ai.model.MultiFileCodeResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiCodeGenerateServiceTest {

    @Resource
    private AiCodeGenerateService aiCodeGenerateService;

    @Test
    void generateHtmlCode() {
        HtmlCodeResult result = aiCodeGenerateService.generateHtmlCode("创建一个程序员的博客，要求不超过20行代码");
        Assertions.assertNotNull(result);
    }

    @Test
    void generateMultiFileCode() {
        MultiFileCodeResult result = aiCodeGenerateService.generateMultiFileCode("创建一个程序员博客的留言板， 要求不超过50行代码");
        Assertions.assertNotNull(result);
    }
}