package com.example.apigateway.config;

import com.example.apigateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;


@Configuration
@CrossOrigin
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://auth"))
                .route("ORDERS-SERVICE", r -> r.path("/orders/**").filters(f -> f.filter(filter)).uri("lb://ORDERS-SERVICE"))
                .route("SHIPPING-SERVICE", r -> r.path("/shipping/**").filters(f -> f.filter(filter)).uri("lb://SHIPPING-SERVICE")).build();
    }

}
