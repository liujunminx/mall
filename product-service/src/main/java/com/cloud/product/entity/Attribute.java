package com.cloud.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spec_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Spec spec;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;
    private String value;
}
