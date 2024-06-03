package com.cloud.product.entity;

import com.cloud.product.enums.CategoryStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long parentId;
    @Column(length = 32, columnDefinition = "varchar(32) default 'ACTIVE'")
    @Enumerated(value = EnumType.STRING)
    private CategoryStatus status = CategoryStatus.ACTIVE;
    @Transient
    private List<Category> children;
}
