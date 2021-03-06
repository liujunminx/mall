package com.cloud.product.service;

import com.cloud.product.entity.ProductCategory;
import com.cloud.product.repository.ProductCategoryRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {

    @Resource
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> listTree() {
        List<ProductCategory> list = productCategoryRepository.findAll();
        Map<Long, List<ProductCategory>> parentListMap = list.stream().collect(Collectors.groupingBy(ProductCategory::getParentId));
        if (parentListMap.containsKey(0L)) {
            List<ProductCategory> root = parentListMap.get(0L);
            for (ProductCategory category : root) {
                setChildren(parentListMap, category);
            }
            return root;
        } else {
            return new ArrayList<>();
        }
    }

    public List<ProductCategory> searchTree(String keyword) {
        return pruneCategories(listTree(), keyword);
    }

    private List<ProductCategory> pruneCategories(List<ProductCategory> categories, String keyword) {
        Iterator<ProductCategory> iterator = categories.iterator();
        while (iterator.hasNext()) {
            ProductCategory category = iterator.next();
            if (!containsKeyword(category, keyword) && !pruneCategory(category, keyword)) {
                iterator.remove();
            }
        }
        return categories;
    }

    private boolean pruneCategory(ProductCategory category, String keyword) {
        if (category.getChildren() != null && !category.getChildren().isEmpty()) {
            Iterator<ProductCategory> iterator = category.getChildren().iterator();
            while (iterator.hasNext()) {
                ProductCategory subCategory = iterator.next();
                if (!containsKeyword(subCategory, keyword) && !pruneCategory(subCategory, keyword))
                    iterator.remove();
            }
            return !category.getChildren().isEmpty();
        }
        return containsKeyword(category, keyword);
    }

    private boolean containsKeyword(ProductCategory category, String keyword) {
        return category.getName().toLowerCase().contains(keyword.toLowerCase());
    }

    public void delete(Long id) {
        List<ProductCategory> allList = productCategoryRepository.findAll();
        Map<Long, List<ProductCategory>> parentIdListMap = allList.stream().collect(Collectors.groupingBy(ProductCategory::getParentId));
        List<ProductCategory> childrenList = findAllChildren(parentIdListMap, id, new ArrayList<>());
        List<Long> idList = new ArrayList<>(childrenList.stream().map(ProductCategory::getId).toList());
        idList.add(id);
        for (Long subId: idList) {
            productCategoryRepository.deleteById(subId);
        }
    }

    private List<ProductCategory> findAllChildren(Map<Long, List<ProductCategory>> map, Long id, List<ProductCategory> targetList) {
        if (map.containsKey(id)) {
            List<ProductCategory> childrenList = map.get(id);
            targetList.addAll(childrenList);
            for (ProductCategory category : childrenList) {
                targetList.addAll(findAllChildren(map, category.getId(), new ArrayList<>()));
            }
        }
        return targetList;
    }

    public void saveAll(List<ProductCategory> list) {
        productCategoryRepository.saveAll(list);
    }

    public void save(ProductCategory category) {
        productCategoryRepository.save(category);
    }

    private void setChildren(Map<Long, List<ProductCategory>> parentListMap, ProductCategory parent) {
        if (parentListMap.containsKey(parent.getId())) {
            List<ProductCategory> children = parentListMap.get(parent.getId());
            parent.setChildren(children);
            for (ProductCategory category: children) {
                setChildren(parentListMap, category);
            }
        }
    }
}
