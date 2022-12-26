package com.atjizhi.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jdzhang
 * @date 2022/12/20 2:49 下午
 */
@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator customeRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        routes.route("path_route_atjizhi",
                r->r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
}