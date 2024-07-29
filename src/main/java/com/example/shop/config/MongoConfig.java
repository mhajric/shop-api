package com.example.shop.config;

import com.example.shop.model.Product;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
        Product product = Product.builder()
                .name("Product 1")
                .description("description")
                .imageUrls(List.of("http://loclhost:8080/images/image1.jpg"))
                .price(BigDecimal.TEN)
                .attributes(Map.of("attrib", "value"))
                .build();
        mongoTemplate.insert(product);
    }
}
