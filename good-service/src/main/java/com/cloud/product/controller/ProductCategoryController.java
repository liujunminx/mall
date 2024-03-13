package com.cloud.product.controller;

import com.cloud.product.dto.CategoryDto;
import com.cloud.product.entity.GoodCategory;
import com.cloud.product.enums.CategoryStatus;
import com.cloud.product.service.GoodCategoryService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
public class ProductCategoryController {
    @Resource
    private GoodCategoryService goodCategoryService;

    @GetMapping("/listTree")
    public ResponseEntity<List<GoodCategory>> listTree() {
        List<GoodCategory> list = goodCategoryService.listTree();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<String> saveAll(@RequestBody List<GoodCategory> list) {
        goodCategoryService.saveAll(list);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody CategoryDto categoryDto) {
        GoodCategory productCategory = GoodCategory
                                            .builder()
                                            .id(categoryDto.getId())
                                            .name(categoryDto.getName())
                                            .parentId(categoryDto.getParentId())
                                            .status(CategoryStatus.ACTIVE)
                                            .build();
        goodCategoryService.save(productCategory);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        goodCategoryService.delete(id);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/search")
    public ResponseEntity<List<GoodCategory>> search(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(goodCategoryService.searchTree(keyword));
    }

    @GetMapping("/findAllLeafs")
    public ResponseEntity<List<GoodCategory>> findAllLeafs() {
        return ResponseEntity.ok(goodCategoryService.findAllLeafs());
    }
}
