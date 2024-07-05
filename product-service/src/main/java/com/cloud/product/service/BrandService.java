package com.cloud.product.service;

import com.cloud.product.entity.Brand;
import com.cloud.product.repository.BrandRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {

    @Resource
    private BrandRepository brandRepository;

    public void save(Brand brand) {
        brandRepository.save(brand);
    }

    public void delete(Long id) {
        brandRepository.deleteById(id);
    }

    public Page<Brand> page(String keyword, Integer pageNumber, Integer pageSize) {
        return brandRepository.findAll((root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(keyword)) {
                list.add(builder.like(root.get("name"), "%" + keyword + "%"));
            }
            Predicate[] pd = new Predicate[list.size()];
            query.where(list.toArray(pd));
            return query.getRestriction();
        }, PageRequest.of(pageNumber, pageSize));
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}
