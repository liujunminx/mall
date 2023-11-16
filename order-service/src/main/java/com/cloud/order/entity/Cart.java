package com.cloud.order.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import java.util.List;

@Data
@RedisHash("cart")
public class Cart {
    @Id
    private Long userId;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Double totalPrice;
    private List<CartItem> items;
}
