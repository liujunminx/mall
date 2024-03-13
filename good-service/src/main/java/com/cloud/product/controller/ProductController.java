package com.cloud.product.controller;

import com.cloud.product.entity.Good;
import com.cloud.product.service.GoodService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class ProductController {

    @Resource
    private GoodService goodService;

    @GetMapping("/listPage")
    public ResponseEntity<Page<Good>> listPage() {
        Page<Good> page = goodService.page();
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Good good) {
        goodService.save(good);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
