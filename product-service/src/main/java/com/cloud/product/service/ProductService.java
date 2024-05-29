package com.cloud.product.service;

import com.cloud.product.entity.Product;
import com.cloud.product.repository.ProductRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Resource
    private ProductRepository productRepository;

    public void save(Product product) {
        productRepository.save(product);
    }
    public Page<Product> page(int pageNumber, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }
}
