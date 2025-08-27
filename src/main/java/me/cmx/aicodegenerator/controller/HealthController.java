package me.cmx.aicodegenerator.controller;

import me.cmx.aicodegenerator.common.BaseResponse;
import me.cmx.aicodegenerator.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health") // 先指定什么的请求方式可以访问 然后才是添加context-path
public class HealthController {

    @GetMapping("/health-check")
    public BaseResponse<Object> healthCheck() {
        return ResultUtils.success("ok");
    }
}

