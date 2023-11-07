package com.cloud.product.service;

import com.cloud.product.entity.ProductCategory;
import com.cloud.product.repository.ProductCategoryRepository;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {

    @Resource
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> listTree() {
        List<ProductCategory> list = productCategoryRepository.findAll();
        Map<Long, List<ProductCategory>> parentMapList = list.stream().collect(Collectors.groupingBy(ProductCategory::getParentId));
        List<ProductCategory> root = parentMapList.get(0L);
        return null;
    }

    private void setChildren(Map<Long, List<ProductCategory>> parentMapList, ProductCategory parent) {
    }
}
