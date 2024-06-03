package com.cloud.product.service;

import com.cloud.product.entity.SpecGroup;
import com.cloud.product.entity.SpecValue;
import com.cloud.product.repository.SpecRepository;
import com.cloud.product.repository.SpecValueRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SpecService {

    @Resource
    private SpecRepository specRepository;

    @Resource
    private SpecValueRepository specValueRepository;

    public void saveSpec(SpecGroup specGroup) {
        specRepository.save(specGroup);
    }

    public void saveSpecValue(SpecValue value) {
        specValueRepository.save(value);
    }

    public Page<SpecGroup> pageSpec(int pageNumber, int pageSize) {
        return specRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public Page<SpecValue> pageSpecValue(int pageNumber, int pageSize) {
        return specValueRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }
}
