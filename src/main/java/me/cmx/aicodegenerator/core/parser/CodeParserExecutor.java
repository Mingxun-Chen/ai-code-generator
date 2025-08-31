package me.cmx.aicodegenerator.core.parser;

import me.cmx.aicodegenerator.exception.BusinessException;
import me.cmx.aicodegenerator.exception.ErrorCode;
import me.cmx.aicodegenerator.model.enums.CodeGenTypeEnum;

/**
 * 代码解析执行器
 * 根据代码生成类型执行相应的解析逻辑
 */
public class CodeParserExecutor {

    private static final HtmlCodeParser htmlCodeParser = new  HtmlCodeParser();

    private static final MultiFileCodeParser multiFileCodeParser = new  MultiFileCodeParser();


    /**
     * 代码解析执行
     * @param codeContent 代码内容
     * @param codeGenType 代码生成类型
     * @return 内容解析结果
     */
    public static Object executeParser(String codeContent, CodeGenTypeEnum codeGenType) {
        return switch (codeGenType) {
            case HTML -> htmlCodeParser.parseCode(codeContent);
            case MULTI_FILE ->  multiFileCodeParser.parseCode(codeContent);
            default -> throw new BusinessException(ErrorCode.SYSTEM_ERROR, "不支持的代码生成类型" + codeGenType);
        };
    }
}
