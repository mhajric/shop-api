package com.example.shop.config;

import com.example.shop.model.Product;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableMongoRepositories(basePackages = "com.example.shop.repository")
public class MongoConfig {

    private final MongoTemplate mongoTemplate;

    @PostConstruct
    public void init() {
        log.info("Initializing MongoDB...");
        mongoTemplate.createCollection("products");
        mongoTemplate.insert(Product.builder().name("Product 1").price(BigDecimal.TEN).build());
    }
}
