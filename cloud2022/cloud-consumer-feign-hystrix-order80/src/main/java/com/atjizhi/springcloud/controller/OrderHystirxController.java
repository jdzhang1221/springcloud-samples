package com.atjizhi.springcloud.controller;

import com.atjizhi.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jdzhang
 * @date 2022/12/18 11:02 下午
 */
@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystirxController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")

//    加了@HystrixCommand属性注解，并且没有写具体回调方法的，就用统一全局的
//    加了@HystrixCommand属性注解，写了具体回调方法的，就用自己的
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",
//            commandProperties = {
//                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",
////                            value="1500") })//只等1.5s

//    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id){
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试 或者 自己运行出错请检查自己,o(╥﹏╥)o";
    }

    //下面是全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍候再试，/(T o T)/~~";
    }

}