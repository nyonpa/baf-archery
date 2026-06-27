package com.archery.api_gateway.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
public class GatewayConfig {


    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {

        return builder.routes()

                .route("auth-service", r -> r
                        .path("/auth/**","/admin/**")
                        .uri("lb://auth-service"))

                .route("team-service", r -> r
                        .path("/teams/**")
                        .uri("lb://team-service"))

                .route("tournament-service", r -> r
                        .path("/tournaments/**")
                        .uri("lb://tournament-service"))

                .build();
    }
}