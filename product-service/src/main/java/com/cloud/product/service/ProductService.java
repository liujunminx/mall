package com.cloud.product.service;

import com.cloud.product.entity.Product;
import com.cloud.product.repository.ProductRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Resource
    private ProductRepository productRepository;

    public void save(List<Product> list) {
        productRepository.saveAll(list);
    }

    public Page<Product> page() {
        return productRepository.findAll(Pageable.ofSize(10));
    }
}
