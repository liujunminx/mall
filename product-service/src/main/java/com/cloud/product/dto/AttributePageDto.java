package com.cloud.product.dto;

import lombok.Builder;
import lombok.Data;

public class AttributePageDto {
    private Long id;
    private String name;
    private String value;
    private String categoryName;
    private String specName;

    public AttributePageDto(Long id, String name, String value, String categoryName, String specName) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.categoryName = categoryName;
        this.specName = specName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }
}
