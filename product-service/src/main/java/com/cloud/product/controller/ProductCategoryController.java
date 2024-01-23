package com.cloud.product.controller;

import com.cloud.product.dto.CategoryDto;
import com.cloud.product.entity.ProductCategory;
import com.cloud.product.enums.CategoryStatus;
import com.cloud.product.service.ProductCategoryService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
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
        return ResponseEntity.ok(list);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<String> saveAll(@RequestBody List<ProductCategory> list) {
        productCategoryService.saveAll(list);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody CategoryDto categoryDto) {
        ProductCategory productCategory = ProductCategory
                                            .builder()
                                            .id(categoryDto.getId())
                                            .name(categoryDto.getName())
                                            .parentId(categoryDto.getParentId())
                                            .status(CategoryStatus.ACTIVE)
                                            .build();
        productCategoryService.save(productCategory);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        productCategoryService.delete(id);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductCategory>> search(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(productCategoryService.searchTree(keyword));
    }

    @GetMapping("/findAllLeafs")
    public ResponseEntity<List<ProductCategory>> findAllLeafs() {
        return ResponseEntity.ok(productCategoryService.findAllLeafs());
    }
}
