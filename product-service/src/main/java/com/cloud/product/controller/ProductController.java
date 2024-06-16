package com.cloud.product.controller;

import com.cloud.product.entity.Product;
import com.cloud.product.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/listPage")
    public ResponseEntity<Page<Product>> listPage(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize) {
        Page<Product> page = productService.page(pageNumber, pageSize);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
