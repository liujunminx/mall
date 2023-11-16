package com.cloud.order.controller;

import com.cloud.order.entity.Cart;
import com.cloud.order.service.CartService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/cart")
@RestController
public class CartController {

    @Resource
    private CartService cartService;

    @GetMapping
    public ResponseEntity<Cart> findById(Long userId) {
        Optional<Cart> cartOptional = cartService.findById(userId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            return ResponseEntity.ok(cart);
        } else {
            throw new RuntimeException("Cart not found");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Cart cart) {
        cartService.save(cart);
        return ResponseEntity.ok("OK");
    }
}
