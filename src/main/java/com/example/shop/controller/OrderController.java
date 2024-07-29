package com.example.shop.controller;

import com.example.shop.controller.response.OrderResponse;
import com.example.shop.converter.OrderConverter;
import com.example.shop.model.Cart;
import com.example.shop.model.Cart.CartItem;
import com.example.shop.model.Order;
import com.example.shop.model.OrderItem;
import com.example.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
@SessionAttributes("cart")
public class OrderController {

    private final OrderService orderService;

    @ModelAttribute("cart")
    public Cart getCart() {
        return new Cart();
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@ModelAttribute("cart") Cart cart) {
        Order order = new Order();

        order.setUserId(UUID.randomUUID().toString()); // TODO: get user ID from authentication context
        order.setDate(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getTotalPrice());
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);

        Order createdOrder = orderService.createOrder(order);

        // Clear the cart after creating the order
        cart.clear();
        OrderResponse orderResponse = OrderConverter.toResponse(createdOrder);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }
}
