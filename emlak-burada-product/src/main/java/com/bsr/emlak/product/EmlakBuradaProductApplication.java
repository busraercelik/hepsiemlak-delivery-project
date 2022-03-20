package com.bsr.emlak.product;

import com.bsr.emlak.commons.constant.CommonConstants;
import com.bsr.emlak.commons.entity.Product;
import com.bsr.emlak.commons.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static com.bsr.emlak.commons.constant.CommonConstants.ADMIN;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bsr.emlak.commons.repository")
@ComponentScan(basePackages = "com.bsr.emlak.*")
@EntityScan("com.bsr.emlak.*")
public class EmlakBuradaProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmlakBuradaProductApplication.class, args);
    }

    private final ProductRepository productRepository;

    @Autowired
    public EmlakBuradaProductApplication(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void saveProducts() {
        Product economy = Product.builder()
                .productName("economy")
                .cost(BigDecimal.valueOf(500))
                .totalAdverts(10)
                .validityPeriodDays(30)
                .build();

        economy.setCreatedBy(ADMIN);
        economy.setCreatedAt(LocalDateTime.now());

        Product premium = Product.builder()
                .productName("premium")
                .cost(BigDecimal.valueOf(1250))
                .totalAdverts(50)
                .validityPeriodDays(30)
                .build();

        premium.setCreatedBy(ADMIN);
        premium.setCreatedAt(LocalDateTime.now());

        if (productRepository.findByProductName(premium.getProductName()).isEmpty() &&
                productRepository.findByProductName(economy.getProductName()).isEmpty()) {
            log.info("Saving economy and premium products!");
            productRepository.saveAll(Arrays.asList(economy, premium));
        } else {
            log.info("Products already exist");
        }
    }
}
