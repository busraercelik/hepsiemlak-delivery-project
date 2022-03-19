package com.bsr.emlak.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bsr.emlak.commons.repository")
@ComponentScan(basePackages = "com.bsr.emlak.*")
@EntityScan("com.bsr.emlak.commons.*")
public class EmlakBuradaEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmlakBuradaEmailApplication.class, args);
    }

}
