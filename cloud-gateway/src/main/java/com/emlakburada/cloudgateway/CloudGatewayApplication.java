package com.emlakburada.cloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/advert-api/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8081/"))
                .route(p -> p
                        .path("/banner-api/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8082/"))
                .route(p -> p
                        .path("/product-api/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8084/"))
                .build();
    }

}
