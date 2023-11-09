package com.cloud.product.controller;

import com.cloud.product.entity.Product;
import com.cloud.product.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.PatternSyntaxException;

@RequestMapping
@RestController
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/listPage")
    public ResponseEntity<Page<Product>> listPage() {
        Page<Product> page = productService.page();
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody List<Product> list) {
        productService.save(list);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
