package com.cloud.product.controller;

import com.cloud.product.entity.Attribute;
import com.cloud.product.service.AttributeService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attr")
public class AttributeController {

    @Resource
    private AttributeService attributeService;

    @GetMapping("/listPage")
    public ResponseEntity<Page<Attribute>> listPage(@RequestParam("pageNumber") Integer pageNumber,
                                              @RequestParam("pageSize") Integer pageSize,
                                              @RequestParam("keyword") String keyword) {
        Page<Attribute> page = attributeService.page(pageNumber, pageSize, keyword);
        return ResponseEntity.ok(page);
    }


    @PutMapping
    public ResponseEntity<Long> save(@RequestBody Attribute attribute) {
        attributeService.save(attribute);
        return ResponseEntity.ok(attribute.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attribute> findById(@PathVariable("id") Long id) {
        Attribute attribute = attributeService.findById(id);
        return ResponseEntity.ok(attribute);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        attributeService.deleteById(id);
        return ResponseEntity.ok(null);
    }
}
