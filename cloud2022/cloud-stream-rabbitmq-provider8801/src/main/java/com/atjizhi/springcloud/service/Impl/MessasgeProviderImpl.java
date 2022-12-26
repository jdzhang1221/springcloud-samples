package com.atjizhi.springcloud.service.Impl;

import com.atjizhi.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author jdzhang
 * @date 2022/12/21 10:08 下午
 */
@EnableBinding(Source.class)//定义消息的推送管道 //不是和controller打交道的service,而是发送消息的推送服务类
public class MessasgeProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output; //消息发送管道
    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*******serial: "+serial);
        return serial;
    }
}