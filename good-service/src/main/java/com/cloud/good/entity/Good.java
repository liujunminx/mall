package com.cloud.good.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "good")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Good extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Long categoryId;
}
