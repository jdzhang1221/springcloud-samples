package com.atjizhi.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author jdzhang
 * @date 2022/12/23 5:34 下午
 */
@RestController
public class PaymentController {

    @Value("${service-url.nacos-user-service}")
    private String serverURL;
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(serverURL + "/payment/nacos/" + id, String.class);
    }
}