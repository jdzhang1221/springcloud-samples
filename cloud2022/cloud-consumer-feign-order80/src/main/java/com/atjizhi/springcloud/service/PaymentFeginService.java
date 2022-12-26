package com.atjizhi.springcloud.service;

import com.atjizhi.springcloud.entities.CommonResult;
import com.atjizhi.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author jdzhang
 * @date 2022/12/18 12:36 下午
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeginService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/fegin/timeout")
    public String paymentFeginTimeout();
}
