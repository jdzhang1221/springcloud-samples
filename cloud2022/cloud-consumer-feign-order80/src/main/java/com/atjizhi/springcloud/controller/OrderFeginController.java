package com.atjizhi.springcloud.controller;

import com.atjizhi.springcloud.entities.CommonResult;
import com.atjizhi.springcloud.entities.Payment;
import com.atjizhi.springcloud.service.PaymentFeginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jdzhang
 * @date 2022/12/18 12:39 下午
 */
@RestController
@Slf4j
public class OrderFeginController {
    @Resource
    private PaymentFeginService paymentFeginService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeginService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/fegin/timeout")
    public String paymentFeginTimeout(){
        //openfegin-ribbon: 一般默认等待一秒,超过一秒报错
        return paymentFeginService.paymentFeginTimeout();
    }
}
