package com.example.shop.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@Getter
@ToString
public class OrderItemResponse {

    private Long id;

    private String productId;

    private int quantity;

    private BigDecimal price;
}
