package com.cloud.product.service;

import com.cloud.product.dto.AttributeDto;
import com.cloud.product.entity.Attribute;
import com.cloud.product.entity.Category;
import com.cloud.product.entity.SpecGroup;
import com.cloud.product.repository.AttributeRepository;
import com.cloud.product.repository.CategoryRepository;
import com.cloud.product.repository.SpecGroupRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.emitter.ScalarAnalysis;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttributeService {

    @Resource
    private AttributeRepository attributeRepository;

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private SpecGroupRepository specGroupRepository;

    public void save(Attribute attribute) {
        attributeRepository.save(attribute);
    }

    public Page<Attribute> page(Integer pageNumber, Integer pageSize, String keyword) {
        return attributeRepository.findAll((root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();
            if(StringUtils.isNotBlank(keyword)){
                list.add(builder.like(root.get("name"), "%" + keyword + "%"));
            }
            Predicate[] pd = new Predicate[list.size()];
            query.where(list.toArray(pd));
            return query.getRestriction();
        }, PageRequest.of(pageNumber, pageSize));
    }

    public AttributeDto findById(Long id) {
        AttributeDto attributeDto = new AttributeDto();
        Attribute attribute = attributeRepository.findById(id).orElse(null);
        attributeDto.setAttribute(attribute);
        if (attribute != null && attribute.getCategoryId() != null) {
            Category category = categoryRepository.findById(attribute.getCategoryId()).orElse(null);
            attributeDto.setCategory(category);
            if (category != null && category.getId() != null) {
                List<SpecGroup> specGroupList = specGroupRepository.findByCategoryId(category.getId());
                attributeDto.setSpecList(specGroupList);
            }
        }
        return attributeDto;
    }

    public void deleteById(Long id) {
        attributeRepository.deleteById(id);
    }
}
