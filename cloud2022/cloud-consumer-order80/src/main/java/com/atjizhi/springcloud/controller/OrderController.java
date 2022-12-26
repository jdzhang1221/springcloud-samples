package com.atjizhi.springcloud.controller;

import com.atjizhi.springcloud.entities.CommonResult;
import com.atjizhi.springcloud.entities.Payment;
import com.atjizhi.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author jdzhang
 * @date 2022/12/14 7:08 下午
 */
@Slf4j
@RestController
public class OrderController {

//    //远程调用的地址
//    public static final String PAYMENT_URL="http://localhost:8001";

    //远程调用的地址，改成提供者在Eureka 上的名称，无需写端口号
    public static final String PAYMENT_URL="http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;


    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",//请求地址
                payment,//请求参数
                CommonResult.class);//返回类型
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,//请求地址
                CommonResult.class);//返回类型
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id")Long id){
        ResponseEntity<CommonResult> eneity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(eneity.getStatusCode().is2xxSuccessful()){
            log.info(eneity.getStatusCode()+"\t"+eneity.getHeaders());
            return eneity.getBody();
        }else {
            return new CommonResult<>(404,"操作失败");
        }
    }

    @GetMapping(value = "/consumer/payment/lb")
    public String getPayment(){
        //获得服务列表
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size()<=0){
            return null;
        }

        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();

        log.info(" "+uri);
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){
        String result = restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin",String.class);
        return result;
    }
}
