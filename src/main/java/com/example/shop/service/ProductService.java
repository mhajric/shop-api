package com.example.shop.service;

import com.example.shop.model.Product;
import com.example.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> getProducts(final int page, final int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Product getProduct(final String id) {
        //TODO: use custom exceptions instead of unchecked exception
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
