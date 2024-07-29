package com.example.shop.controller;

import com.example.shop.controller.response.ProductResponse;
import com.example.shop.converter.ProductConverter;
import com.example.shop.model.Product;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        log.info("Get products page: {}, size: {}", page, size);
        final Page<Product> products = productService.getProducts(page, size);
        final Page<ProductResponse> productResponses = products.map(ProductConverter::toResponse);
        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id) {
        final Product product = productService.getProduct(id);
        final ProductResponse productResponse = ProductConverter.toResponse(product);
        return ResponseEntity.ok(productResponse);
    }
}
