package com.cloud.good.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "good_image")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodImage extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productId;
    private String imageUrl;
    private Boolean isMain;
    private LocalDateTime createdTime;

    @PrePersist
    protected void onCreate() {
        createdTime = LocalDateTime.now();
    }
}
