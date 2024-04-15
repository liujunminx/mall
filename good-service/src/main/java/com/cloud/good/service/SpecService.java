package com.cloud.good.service;

import com.cloud.good.entity.Spec;
import com.cloud.good.entity.SpecValue;
import com.cloud.good.repository.SpecRepository;
import com.cloud.good.repository.SpecValueRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SpecService {

    @Resource
    private SpecRepository specRepository;

    @Resource
    private SpecValueRepository specValueRepository;

    public void saveSpec(Spec spec) {
        specRepository.save(spec);
    }

    public void saveSpecValue(SpecValue value) {
        specValueRepository.save(value);
    }

    public Page<Spec> pageSpec(int pageNumber, int pageSize) {
        return specRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public Page<SpecValue> pageSpecValue(int pageNumber, int pageSize) {
        return specValueRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }
}
