package com.example.emlakburadaemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class EmlakBuradaEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmlakBuradaEmailApplication.class, args);
    }

}
