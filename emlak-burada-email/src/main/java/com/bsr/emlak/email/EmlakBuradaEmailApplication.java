package com.bsr.emlak.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.bsr.emlak.commons.*")
@EntityScan("com.bsr.emlak.commons.*")
public class EmlakBuradaEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmlakBuradaEmailApplication.class, args);
    }

}
