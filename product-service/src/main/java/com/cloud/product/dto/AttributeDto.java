package com.cloud.product.dto;

import com.cloud.product.entity.Attribute;
import com.cloud.product.entity.Category;
import com.cloud.product.entity.SpecGroup;
import lombok.Data;

import java.util.List;

@Data
public class AttributeDto {
    private Attribute attribute;
    private Category category;
    private List<SpecGroup> specList;
}
