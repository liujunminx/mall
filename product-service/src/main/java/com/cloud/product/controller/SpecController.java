package com.cloud.product.controller;

import com.cloud.product.entity.SpecGroup;
import com.cloud.product.entity.SpecValue;
import com.cloud.product.service.SpecService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spec")
public class SpecController {

    @Resource
    private SpecService specService;

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody SpecGroup specGroup) {
        specService.saveSpec(specGroup);
        return ResponseEntity.ok(specGroup.getId());
    }

    @GetMapping
    public ResponseEntity<SpecGroup> get(@PathVariable("id") Long id) {
        SpecGroup sg = specService.findById(id);
        return ResponseEntity.ok(sg);
    }

    @PostMapping("/value")
    public ResponseEntity<Long> save(@RequestBody SpecValue specValue) {
        specService.saveSpecValue(specValue);
        return ResponseEntity.ok(specValue.getSpecValueId());
    }

    @GetMapping("/listPage")
    public ResponseEntity<Page<SpecGroup>> listPage(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize) {
        Page<SpecGroup> page = specService.pageSpec(pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }
}
