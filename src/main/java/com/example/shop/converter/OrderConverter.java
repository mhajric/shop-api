package com.example.shop.converter;

import com.example.shop.controller.response.OrderResponse;
import com.example.shop.model.Order;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderConverter {

    public static OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .date(order.getDate())
                .orderItems(order.getOrderItems().stream().map(OrderItemConverter::toResponse).collect(Collectors.toList()))
                .status(order.getStatus())
                .build();
    }
}
