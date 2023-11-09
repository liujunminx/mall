package com.cloud.product.controller;

import com.cloud.product.entity.ProductCategory;
import com.cloud.product.service.ProductCategoryService;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
public class ProductCategoryController {
    @Resource
    private ProductCategoryService productCategoryService;

    @GetMapping("/listTree")
    public ResponseEntity<List<ProductCategory>> listTree() {
        List<ProductCategory> list = productCategoryService.listTree();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody List<ProductCategory> list) {
        productCategoryService.save(list);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
