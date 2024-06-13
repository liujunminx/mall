package com.cloud.product.service;

import com.cloud.product.dto.SpecDto;
import com.cloud.product.entity.SpecGroup;
import com.cloud.product.repository.CategoryRepository;
import com.cloud.product.repository.SpecGroupRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecService {

    @Resource
    private SpecGroupRepository specGroupRepository;

    @Resource
    private CategoryRepository categoryRepository;

    public void saveSpec(SpecGroup specGroup) {
        specGroupRepository.save(specGroup);
    }

    public Page<SpecGroup> pageSpecByName(int pageNumber, int pageSize, String keyword) {
        return specGroupRepository.findAll((root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();
            if(StringUtils.isNotBlank(keyword)) {
                list.add(builder.like(root.get("name"), "%" + keyword + "%"));
            }
            Predicate[] pd = new Predicate[list.size()];
            query.where(list.toArray(pd));
            return query.getRestriction();
        }, PageRequest.of(pageNumber, pageSize));
    }

    public SpecDto findDtoById(Long id) {
        SpecGroup specGroup = specGroupRepository.findById(id).orElse(null);
        SpecDto specDto = new SpecDto();
        if (specGroup != null) {
            specDto.setSpecGroup(specGroup);
            categoryRepository.findById(specGroup.getCategoryId()).ifPresent(specDto::setCategory);
        }
        return specDto;
    }

    public void deleteById(Long id) {
        specGroupRepository.deleteById(id);
    }
}
