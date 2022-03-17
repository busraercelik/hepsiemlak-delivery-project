package com.bsr.emlak.burada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableEurekaClient
@EnableFeignClients
@EnableJpaRepositories(basePackages = "com.bsr.emlak.commons.repository")
@ComponentScan(basePackages = "com.bsr.emlak.*")
@EntityScan("com.bsr.emlak.*")
public class EmlakBuradaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmlakBuradaApplication.class, args);
    }

}
