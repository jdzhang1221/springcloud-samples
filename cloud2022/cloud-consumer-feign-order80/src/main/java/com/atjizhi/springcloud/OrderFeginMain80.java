package com.atjizhi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author jdzhang
 * @date 2022/12/18 12:34 下午
 */
@SpringBootApplication
@EnableFeignClients
public class OrderFeginMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeginMain80.class,args);
    }
}
