package com.cloud.product.service;

import com.cloud.product.entity.Spec;
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
public class SpecService {

    @Resource
    private SpecRepository specRepository;

    @Resource
    private CategoryRepository categoryRepository;

    public Long saveSpec(Spec spec) {
        Spec save = specRepository.save(spec);
        return save.getId();
    }

    public Page<Spec> pageByKeyword(int pageNumber, int pageSize, String keyword) {
        return specRepository.findAll((root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();
            if(StringUtils.isNotBlank(keyword)) {
                list.add(builder.like(root.get("name"), "%" + keyword + "%"));
            }
            Predicate[] pd = new Predicate[list.size()];
            query.where(list.toArray(pd));
            return query.getRestriction();
        }, PageRequest.of(pageNumber, pageSize));
    }

    public Spec findDtoById(Long id) {
        return specRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        specRepository.deleteById(id);
    }

    public List<Spec> findByCategoryId(Long categoryId) {
        return specRepository.findByCategoryId(categoryId);
    }
}
