package com.cloud.good.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "spec")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Spec extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long specId;
    private String specName;
}
