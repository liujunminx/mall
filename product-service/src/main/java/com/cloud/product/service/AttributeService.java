package com.cloud.product.service;

import com.cloud.product.entity.Attribute;
import com.cloud.product.repository.AttributeRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttributeService {

    @Resource
    private AttributeRepository attributeRepository;

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

    public Attribute findById(Long id) {
        return attributeRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        attributeRepository.deleteById(id);
    }
}
