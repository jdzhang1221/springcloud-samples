package com.atjizhi.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author jdzhang
 * @date 2022/12/21 11:54 下午
 */
@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController8804 {
    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("消费者2号，-------->接收到的消息是： "+message.getPayload()+"\t port: "+serverPort);
    }

}
