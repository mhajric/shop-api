package com.example.shop.service;

import com.example.shop.model.Order;
import com.example.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createOrder(final Order order) {
        return orderRepository.save(order);
    }
}
