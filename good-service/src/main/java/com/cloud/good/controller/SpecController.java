package com.cloud.good.controller;

import com.cloud.good.entity.Spec;
import com.cloud.good.entity.SpecValue;
import com.cloud.good.service.SpecService;
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
    public ResponseEntity<Long> save(@RequestBody Spec spec) {
        specService.saveSpec(spec);
        return ResponseEntity.ok(spec.getSpecId());
    }

    @PostMapping("/value")
    public ResponseEntity<Long> save(@RequestBody SpecValue specValue) {
        specService.saveSpecValue(specValue);
        return ResponseEntity.ok(specValue.getSpecValueId());
    }

    @GetMapping("/listPage")
    public ResponseEntity<Page<Spec>> listPage(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize) {
        Page<Spec> page = specService.pageSpec(pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }
}
