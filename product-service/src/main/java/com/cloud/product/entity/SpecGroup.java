package com.cloud.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "spec_group")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecGroup extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long categoryId;
}
