package com.cloud.good.controller;

import com.cloud.good.entity.Good;
import com.cloud.good.service.GoodService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class GoodController {

    @Resource
    private GoodService goodService;

    @GetMapping("/listPage")
    public ResponseEntity<Page<Good>> listPage(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize) {
        Page<Good> page = goodService.page(pageNumber, pageSize);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Good good) {
        goodService.save(good);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
