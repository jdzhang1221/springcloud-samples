package com.atjizhi.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author jdzhang
 * @date 2022/12/18 10:41 下午
 */
@Service
public class PaymentService {
    //正常访问的方法
    public String paymentInfo_OK(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + "  paymentInfo_OK,id:  " + id + "\t" + "o(n_n)o哈哈·";
    }


    //降级配置：在里面指定超时/出错的回调方法，作为兜底方法
    @HystrixCommand(fallbackMethod = "payment_TimeOutHandler",//超时回调方法
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "3000")})//超时时间
    public String paymentInfo_Timeout(Integer id) {
        // int s =10 /0;
        int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + "  paymentInfo_Timeout,id:  " + id + "\t" + "o(n_n)o哈哈· 耗时" + timeNumber + "秒";
    }


    public String payment_TimeOutHandler(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + "  8001系统繁忙，请稍后再试:  " + id + "\t" + "o(T_T)o";
    }


    //====服务熔断

    /**
     * HystrixCommandProperties里面有这些属性
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸
    }) // 在10s内10次请求有60%失败 // 请求次数要先满足，再看百分比
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("*****id 不为负数");
        }
        /**
         * huTool工具包的方法
         * 在自定义的cloud-api-commons里引入了
         */
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号： " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id不能为负数，请稍候再试，/(T o T)/~~ id: " + id;
    }

}