package com.example.shop.repository;

import com.example.shop.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    public void shouldSaveProduct() {
        // given
        Product product = Product.builder()
                .id("1")
                .name("Test Product")
                .build();

        // when
        productRepository.save(product);

        // then
        Product saved = mongoTemplate.findById("1", Product.class);
        assertEquals(product, saved);
    }

    @Test
    public void shouldFindAllProducts() {
        // given
        Product product1 = Product.builder()
                .id("1")
                .name("Test Product 1")
                .build();
        Product product2 = Product.builder()
                .id("2")
                .name("Test Product 2")
                .build();
        mongoTemplate.save(product1);
        mongoTemplate.save(product2);

        // when
        Collection<Product> allProducts = productRepository.findAll();

        // then
        assertEquals(2, allProducts.size());
    }

}
