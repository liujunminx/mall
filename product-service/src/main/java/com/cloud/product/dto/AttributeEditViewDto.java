package com.cloud.product.dto;

import com.cloud.product.entity.Attribute;
import com.cloud.product.entity.Spec;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
public class AttributeEditViewDto {
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Attribute attr;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Spec> specList;
}
