package com.atjizhi.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jdzhang
 * @date 2022/12/21 4:18 下午
 */
@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}") //github里的yml文件里的内容
    private String configInfo;

    @RequestMapping("/configInfo")
    public String getConfigInfo(){
        return "serverPort: "+serverPort+"\t\n\n configInfo: "+configInfo;
    }
}
