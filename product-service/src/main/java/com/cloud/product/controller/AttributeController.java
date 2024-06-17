package com.cloud.product.controller;

import com.cloud.product.dto.AttributeEditViewDto;
import com.cloud.product.dto.AttributePageDto;
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

    @GetMapping("/page")
    public ResponseEntity<Page<AttributePageDto>> page(@RequestParam("pageNumber") Integer pageNumber,
                                                       @RequestParam("pageSize") Integer pageSize,
                                                       @RequestParam("keyword") String keyword) {
        Page<AttributePageDto> page = attributeService.page(pageNumber, pageSize, keyword);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody Attribute attribute) {
        attributeService.save(attribute);
        return ResponseEntity.ok(attribute.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attribute> findById(@PathVariable("id") Long id) {
        Attribute attribute = attributeService.findById(id);
        return ResponseEntity.ok(attribute);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<AttributeEditViewDto> viewEdit(@PathVariable("id") Long id) {
        AttributeEditViewDto dto = attributeService.findEditView(id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        attributeService.deleteById(id);
        return ResponseEntity.ok(null);
    }
}
