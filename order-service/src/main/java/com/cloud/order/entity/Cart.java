package com.cloud.order.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Cart {
    private Long id;
    private Long userId;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private List<CartItem> items;
}
