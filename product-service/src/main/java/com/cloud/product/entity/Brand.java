package com.cloud.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brand")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Brand extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String imageUrl;
}
