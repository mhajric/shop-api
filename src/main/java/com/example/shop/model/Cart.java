package com.example.shop.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@ToString
public class Cart {

    private final Set<CartItem> items = new LinkedHashSet<>();

    public void addItem(Product product, int quantity) {
        Optional<CartItem> optionalCartItem = items.stream().filter(i -> i.getProductId().equals(product.getId())).findFirst();
        if (optionalCartItem.isPresent()) {
            CartItem item = optionalCartItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            BigDecimal initialPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            CartItem item = new CartItem(product.getId(), product.getPrice(), quantity, initialPrice);
            items.add(item);
        }
    }

    public void removeItem(String productId) {
        items.removeIf(i -> i.getProductId().equals(productId));
    }

    public void clear() {
        items.clear();
    }

    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode(of = "productId")
    public static class CartItem {

        private String productId;

        private BigDecimal price;

        private int quantity;

        private BigDecimal totalPrice;

    }
}
