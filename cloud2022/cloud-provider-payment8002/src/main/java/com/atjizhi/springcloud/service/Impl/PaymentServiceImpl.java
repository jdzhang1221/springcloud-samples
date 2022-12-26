package com.atjizhi.springcloud.service.Impl;

import com.atjizhi.springcloud.dao.PaymentDao;
import com.atjizhi.springcloud.entities.Payment;
import com.atjizhi.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jdzhang
 * @date 2022/12/14 11:21 上午
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
