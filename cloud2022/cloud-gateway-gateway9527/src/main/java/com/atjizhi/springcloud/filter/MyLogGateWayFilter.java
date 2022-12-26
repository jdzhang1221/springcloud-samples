package com.atjizhi.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author jdzhang
 * @date 2022/12/20 5:17 下午
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("************com in MyLogGateWayFilter:  "+new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        //合法性检验
        if(uname==null){
            log.info("************用户名为null, 非法用户, o(T~~T)o");
            //设置 response 状态码 406
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            //完成请求调用
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    // 返回值是加载顺序，一般全局的都是第一位加载
    //这个返回的数值越小，上面的filter优先级就越高
    @Override
    public int getOrder() {
        return 0;
    }
}