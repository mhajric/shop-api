package com.example.shop.controller.response;

import com.example.shop.common.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@ToString
public class OrderResponse {

    private Long id;

    private String userId;

    private LocalDateTime date;

    private List<OrderItemResponse> orderItems;

    private OrderStatus status;
}
