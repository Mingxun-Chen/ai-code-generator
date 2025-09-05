package me.cmx.aicodegenerator.service;

public interface ScreenshotService {

    /**
     * 生成并上传截图
     * @param webUrl 截图网址
     * @return 对象存储的返回 URL
     */
    String generateAndUploadScreenshot(String webUrl);
}
