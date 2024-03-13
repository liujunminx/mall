package com.cloud.product.service;

import com.cloud.product.entity.GoodCategory;
import com.cloud.product.repository.GoodCategoryRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GoodCategoryService {

    @Resource
    private GoodCategoryRepository goodCategoryRepository;

    public List<GoodCategory> listTree() {
        List<GoodCategory> list = goodCategoryRepository.findAll();
        Map<Long, List<GoodCategory>> parentListMap = list.stream().collect(Collectors.groupingBy(GoodCategory::getParentId));
        if (parentListMap.containsKey(0L)) {
            List<GoodCategory> root = parentListMap.get(0L);
            for (GoodCategory category : root) {
                setChildren(parentListMap, category);
            }
            return root;
        } else {
            return new ArrayList<>();
        }
    }

    public List<GoodCategory> searchTree(String keyword) {
        return pruneCategories(listTree(), keyword);
    }

    private List<GoodCategory> pruneCategories(List<GoodCategory> categories, String keyword) {
        Iterator<GoodCategory> iterator = categories.iterator();
        while (iterator.hasNext()) {
            GoodCategory category = iterator.next();
            if (!containsKeyword(category, keyword) && !pruneCategory(category, keyword)) {
                iterator.remove();
            }
        }
        return categories;
    }

    private boolean pruneCategory(GoodCategory category, String keyword) {
        if (category.getChildren() != null && !category.getChildren().isEmpty()) {
            Iterator<GoodCategory> iterator = category.getChildren().iterator();
            while (iterator.hasNext()) {
                GoodCategory subCategory = iterator.next();
                if (!containsKeyword(subCategory, keyword) && !pruneCategory(subCategory, keyword))
                    iterator.remove();
            }
            return !category.getChildren().isEmpty();
        }
        return containsKeyword(category, keyword);
    }

    private boolean containsKeyword(GoodCategory category, String keyword) {
        return category.getName().toLowerCase().contains(keyword.toLowerCase());
    }

    public void delete(Long id) {
        List<GoodCategory> allList = goodCategoryRepository.findAll();
        Map<Long, List<GoodCategory>> parentIdListMap = allList.stream().collect(Collectors.groupingBy(GoodCategory::getParentId));
        List<GoodCategory> childrenList = findAllChildren(parentIdListMap, id, new ArrayList<>());
        List<Long> idList = new ArrayList<>(childrenList.stream().map(GoodCategory::getId).toList());
        idList.add(id);
        for (Long subId: idList) {
            goodCategoryRepository.deleteById(subId);
        }
    }

    private List<GoodCategory> findAllChildren(Map<Long, List<GoodCategory>> map, Long id, List<GoodCategory> targetList) {
        if (map.containsKey(id)) {
            List<GoodCategory> childrenList = map.get(id);
            targetList.addAll(childrenList);
            for (GoodCategory category : childrenList) {
                targetList.addAll(findAllChildren(map, category.getId(), new ArrayList<>()));
            }
        }
        return targetList;
    }

    public List<GoodCategory> findAllLeafs() {
        return goodCategoryRepository.findAllLeafs();
    }

    public void saveAll(List<GoodCategory> list) {
        goodCategoryRepository.saveAll(list);
    }

    public void save(GoodCategory category) {
        goodCategoryRepository.save(category);
    }

    private void setChildren(Map<Long, List<GoodCategory>> parentListMap, GoodCategory parent) {
        if (parentListMap.containsKey(parent.getId())) {
            List<GoodCategory> children = parentListMap.get(parent.getId());
            parent.setChildren(children);
            for (GoodCategory category: children) {
                setChildren(parentListMap, category);
            }
        }
    }
}
