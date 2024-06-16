package com.cloud.product.controller;

import com.cloud.product.dto.CategoryDto;
import com.cloud.product.entity.Category;
import com.cloud.product.enums.CategoryStatus;
import com.cloud.product.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/listTree")
    public ResponseEntity<List<Category>> listTree() {
        List<Category> list = categoryService.listTree();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<String> saveAll(@RequestBody List<Category> list) {
        categoryService.saveAll(list);
        return ResponseEntity.ok("OK");
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody CategoryDto categoryDto) {
        Category category = Category
                                .builder()
                                .id(categoryDto.getId())
                                .name(categoryDto.getName())
                                .parentId(categoryDto.getParentId())
                                .status(CategoryStatus.ACTIVE)
                                .build();
        categoryService.save(category);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(categoryService.searchTree(keyword));
    }

    @GetMapping("/findAllLeafs")
    public ResponseEntity<List<Category>> findAllLeafs() {
        return ResponseEntity.ok(categoryService.findAllLeafs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findParentById(@PathVariable(value = "id") Long id) {
        Category parent = categoryService.findById(id);
        return ResponseEntity.ok(parent);
    }
}
