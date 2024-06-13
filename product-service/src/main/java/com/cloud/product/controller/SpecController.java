package com.cloud.product.controller;

import com.cloud.product.dto.SpecDto;
import com.cloud.product.entity.SpecGroup;
import com.cloud.product.entity.Attribute;
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

    @PutMapping
    public ResponseEntity<Long> save(@RequestBody SpecGroup specGroup) {
        specService.saveSpec(specGroup);
        return ResponseEntity.ok(specGroup.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecDto> get(@PathVariable("id") Long id) {
        SpecDto specDto = specService.findDtoById(id);
        return ResponseEntity.ok(specDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        specService.deleteById(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/listPage")
    public ResponseEntity<Page<SpecGroup>> listPage(@RequestParam("pageNumber") Integer pageNumber,
                                                    @RequestParam("pageSize") Integer pageSize,
                                                    @RequestParam("keyword") String keyword) {

        Page<SpecGroup> page = specService.pageSpecByName(pageNumber, pageSize, keyword);
        return ResponseEntity.ok(page);
    }
}
