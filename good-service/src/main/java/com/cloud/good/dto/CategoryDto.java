package com.cloud.good.dto;

import com.cloud.good.enums.CategoryStatus;
import lombok.Data;

@Data
public class CategoryDto {

    private Long id;
    private Long parentId;
    private String name;
    private CategoryStatus status = CategoryStatus.ACTIVE;
}
