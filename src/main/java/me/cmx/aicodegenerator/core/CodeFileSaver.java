package me.cmx.aicodegenerator.core;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import me.cmx.aicodegenerator.ai.model.HtmlCodeResult;
import me.cmx.aicodegenerator.ai.model.MultiFileCodeResult;
import me.cmx.aicodegenerator.model.enums.CodeGenTypeEnum;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Deprecated
public class CodeFileSaver {

    // 文件保存根目录
    private static final String FILE_SAVE_ROOT_DIR = System.getProperty("user.dir") + "/tmp/code_output";

    /**
     * 构建唯一路径目录: tmp/code_output/biz_Type_snowflakeId
     * @param bizType
     * @return
     */
    private static String buildUniqueDir(String bizType) {
        String uniqueDirName = StrUtil.format("{}_{}", bizType, IdUtil.getSnowflakeNextIdStr());
        String dirPath = FILE_SAVE_ROOT_DIR + File.separator + uniqueDirName;
        FileUtil.mkdir(dirPath);
        return dirPath;
    }

    /**
     * 写入单个文件
     */
    private static void writeToFile(String dirPath, String filename, String content) {
        String filePath = dirPath + File.separator + filename;
        FileUtil.writeString(content, filePath, StandardCharsets.UTF_8);
    }

    /**
     * 保存 html 文件代码
     * @param result
     * @return
     */
    public static File saveHtmlCodeResult(HtmlCodeResult result) {
        String dirPath = buildUniqueDir(CodeGenTypeEnum.HTML.getValue());
        writeToFile(dirPath, "index.html", result.getHtmlCode());
        return new File(dirPath);
    }

    /**
     * 保存多文件代码
     * @param result
     * @return
     */
    public static File saveMultiFileCodeResult(MultiFileCodeResult result) {
        String dirPath = buildUniqueDir(CodeGenTypeEnum.MULTI_FILE.getValue());
        writeToFile(dirPath, "index.html", result.getHtmlCode());
        writeToFile(dirPath, "style.css", result.getCssCode());
        writeToFile(dirPath, "script.js", result.getJsCode());
        return new File(dirPath);
    }
}
