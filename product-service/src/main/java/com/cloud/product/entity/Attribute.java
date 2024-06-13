package com.cloud.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attribute")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attribute extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long specId;
    private Long categoryId;
}
