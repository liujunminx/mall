package com.cloud.product.controller;

import com.cloud.product.entity.ProductCategory;
import com.cloud.product.service.ProductCategoryService;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/category")
@RestController
public class ProductCategoryController {
    @Resource
    private ProductCategoryService productCategoryService;

    @GetMapping("/listTree")
    public List<ProductCategory> listTree() {
        return productCategoryService.listTree();
    }
}
