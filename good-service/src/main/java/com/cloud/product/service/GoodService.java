package com.cloud.product.service;

import com.cloud.product.entity.Good;
import com.cloud.product.repository.GoodRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GoodService {

    @Resource
    private GoodRepository goodRepository;

    public void save(Good good) {
        goodRepository.save(good);
    }
    public Page<Good> page() {
        return goodRepository.findAll(Pageable.ofSize(10));
    }
}
