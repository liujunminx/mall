package com.cloud.product.controller;

import com.cloud.product.entity.Spec;
import com.cloud.product.service.SpecService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spec")
public class SpecController {

    @Resource
    private SpecService specService;

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody Spec spec) {
        Long specId = specService.saveSpec(spec);
        return ResponseEntity.ok(specId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Spec> get(@PathVariable("id") Long id) {
        Spec spec = specService.findDtoById(id);
        return ResponseEntity.ok(spec);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        specService.deleteById(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Spec>> page(@RequestParam("pageNumber") Integer pageNumber,
                                           @RequestParam("pageSize") Integer pageSize,
                                           @RequestParam("keyword") String keyword) {
        Page<Spec> page = specService.pageByKeyword(pageNumber, pageSize, keyword);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Spec>> findByCategoryId(@PathVariable("categoryId") Long categoryId) {
        List<Spec> specList = specService.findByCategoryId(categoryId);
        return ResponseEntity.ok(specList);
    }
}
