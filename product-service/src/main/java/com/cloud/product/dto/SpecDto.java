package com.cloud.product.dto;

import com.cloud.product.entity.Category;
import com.cloud.product.entity.SpecGroup;
import lombok.Data;

@Data
public class SpecDto {
    private SpecGroup specGroup;
    private Category category;
}
