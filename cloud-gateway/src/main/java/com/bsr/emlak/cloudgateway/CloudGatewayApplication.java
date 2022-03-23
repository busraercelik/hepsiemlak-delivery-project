package com.bsr.emlak.cloudgateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bsr.emlak.commons.repository")
@ComponentScan(basePackages = "com.bsr.emlak.*")
@EntityScan("com.bsr.emlak.*")
public class CloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayApplication.class, args);
    }

    @Value("${emlak-burada-url}")
    private String buradaServiceUrl;
    @Value("${emlak-burada-banner-url}")
    private String bannerServiceUrl;
    @Value("${emlak-burada-product-url}")
    private String productServiceUrl;

    @Bean
    public RouteLocator routeDefinition(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/advert-api/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri(buradaServiceUrl))
                .route(p -> p
                        .path("/banner-api/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri(bannerServiceUrl))
                .route(p -> p
                        .path("/product-api/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri(productServiceUrl))
                .build();
    }

}
