package com.example.shop.controller.response;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@Getter
@ToString
public class ProductResponse {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private String[] imageUrls;

    private Object[] attributes;
}
