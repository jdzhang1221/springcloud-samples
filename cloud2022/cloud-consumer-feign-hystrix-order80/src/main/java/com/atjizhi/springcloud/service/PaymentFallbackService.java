package com.atjizhi.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author jdzhang
 * @date 2022/12/19 10:55 下午
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService fall back-paymentInfo_OK,o(T~~T)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFallbackService fall back-paymentInfo_TimeOut,o(T~~T)o";
    }
}
