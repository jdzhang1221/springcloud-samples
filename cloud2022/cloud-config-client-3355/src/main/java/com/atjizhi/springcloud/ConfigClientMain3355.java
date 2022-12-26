package com.atjizhi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author jdzhang
 * @date 2022/12/20 7:57 下午
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigClientMain3355{
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3355.class, args);
    }
}