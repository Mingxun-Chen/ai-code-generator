package me.cmx.aicodegenerator.service;

import jakarta.servlet.http.HttpServletResponse;

public interface ProjectDownloadService {

    /**
     * 下载项目代码
     * @param projectPath 项目路径
     * @param downloadFileName 下载文件名
     * @param response 响应头
     */
    void downloadProjectAsZip(String projectPath, String downloadFileName, HttpServletResponse response);
}
