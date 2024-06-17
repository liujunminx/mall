package com.cloud.product.service;

import com.cloud.product.dto.AttributeEditViewDto;
import com.cloud.product.dto.AttributePageDto;
import com.cloud.product.entity.Attribute;
import com.cloud.product.entity.Spec;
import com.cloud.product.repository.AttributeRepository;
import com.cloud.product.repository.CategoryRepository;
import com.cloud.product.repository.SpecRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttributeService {

    @Resource
    private AttributeRepository attributeRepository;

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private SpecRepository specRepository;

    public void save(Attribute attribute) {
        attributeRepository.save(attribute);
    }

    public Page<AttributePageDto> page(Integer pageNumber, Integer pageSize, String keyword) {
        return attributeRepository.findAllAttributes(keyword, PageRequest.of(pageNumber, pageSize));
    }

    public Attribute findById(Long id) {
        Attribute attribute = attributeRepository.findById(id).orElse(null);
        return attribute;
    }

    public AttributeEditViewDto findEditView(Long id) {
        AttributeEditViewDto dto = new AttributeEditViewDto();
        Attribute attribute = attributeRepository.findById(id).orElse(null);
        dto.setAttr(attribute);
        if (attribute != null && attribute.getCategory() != null) {
            List<Spec> specList = specRepository.findByCategoryId(attribute.getCategory().getId());
            dto.setSpecList(specList);
        }
        return dto;
    }

    public void deleteById(Long id) {
        attributeRepository.deleteById(id);
    }
}
