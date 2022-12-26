package com.atjizhi.springcloud.controller;

import com.atjizhi.springcloud.entities.CommonResult;
import com.atjizhi.springcloud.entities.Payment;
import com.atjizhi.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author jdzhang
 * @date 2022/12/14 11:29 上午
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}") //读取yml中的值
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("***********插入结果：" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort:" + serverPort, result);
        } else {
            return new CommonResult(404, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("***********查询结果：" + payment);

        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payment);
        } else {
            return new CommonResult(404, "查询失败", null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPayment(){
        return serverPort;
    }
}
