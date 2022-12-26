package com.atjizhi.springcloud.service;

import com.atjizhi.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author jdzhang
 * @date 2022/12/14 11:20 上午
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
