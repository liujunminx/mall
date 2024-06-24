package com.cloud.product.controller;

import com.cloud.product.entity.Brand;
import com.cloud.product.repository.BrandRepository;
import com.cloud.product.service.BrandService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Resource
    private BrandService brandService;

    @GetMapping("/page")
    public ResponseEntity<Page<Brand>> page(@RequestParam("pageNumber") Integer pageNumber,
                                            @RequestParam("pageSize") Integer pageSize,
                                            @RequestParam("keyword") String keyword) {
        Page<Brand> page = brandService.page(keyword, pageNumber, pageSize);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody Brand brand) {
        brandService.save(brand);
        return ResponseEntity.ok(brand.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        brandService.delete(id);
        return ResponseEntity.ok(null);
    }
}
