package com.cloud.order.service;

import com.cloud.order.entity.Cart;
import com.cloud.order.repository.CartRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Resource
    private CartRepository cartRepository;

    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    public Optional<Cart> findById(Long userId) {
        return cartRepository.findById(userId);
    }
}
