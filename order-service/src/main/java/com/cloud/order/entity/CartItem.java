package com.cloud.order.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CartItem {
    private Long id;
    private Long productId;
    private Integer quantity;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
