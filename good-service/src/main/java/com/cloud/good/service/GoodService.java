package com.cloud.good.service;

import com.cloud.good.entity.Good;
import com.cloud.good.repository.GoodRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GoodService {

    @Resource
    private GoodRepository goodRepository;

    public void save(Good good) {
        goodRepository.save(good);
    }
    public Page<Good> page(int pageNumber, int pageSize) {
        return goodRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }
}
