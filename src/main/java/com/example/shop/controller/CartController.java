package com.example.shop.controller;

import com.example.shop.model.Cart;
import com.example.shop.model.Product;
import com.example.shop.service.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
@SessionAttributes("cart")
public class CartController {

    private final ProductService productService;

    @ModelAttribute("cart")
    public Cart getCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addItemToCart(@RequestParam String productId, @RequestParam int quantity, @Parameter(hidden = true) @ModelAttribute("cart") Cart cart) {
        Product product = productService.getProduct(productId);
        cart.addItem(product, quantity);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Cart> removeItemFromCart(@RequestParam String productId, @Parameter(hidden = true) @ModelAttribute("cart") Cart cart) {
        cart.removeItem(productId);
        return ResponseEntity.ok(cart);
    }

    @GetMapping
    public ResponseEntity<Cart> viewCart(@Parameter(hidden = true) @ModelAttribute("cart") Cart cart) {
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/clear")
    public ResponseEntity<Cart> clearCart(@Parameter(hidden = true) @ModelAttribute("cart") Cart cart) {
        cart.clear();
        return ResponseEntity.ok(cart);
    }
}

