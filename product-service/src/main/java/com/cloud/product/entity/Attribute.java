package com.cloud.product.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private String values;
}
