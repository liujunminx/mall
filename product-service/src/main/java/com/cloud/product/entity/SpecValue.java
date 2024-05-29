package com.cloud.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "spec_value")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecValue extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long specValueId;
    private String specValue;
    private Long specId;
}
