package com.cloud.good.service;

import com.cloud.good.entity.Category;
import com.cloud.good.repository.CategoryRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Resource
    private CategoryRepository categoryRepository;

    public List<Category> listTree() {
        List<Category> list = categoryRepository.findAll();
        Map<Long, List<Category>> parentListMap = list.stream().collect(Collectors.groupingBy(Category::getParentId));
        if (parentListMap.containsKey(0L)) {
            List<Category> root = parentListMap.get(0L);
            for (Category category : root) {
                setChildren(parentListMap, category);
            }
            return root;
        } else {
            return new ArrayList<>();
        }
    }

    public List<Category> searchTree(String keyword) {
        return pruneCategories(listTree(), keyword);
    }

    private List<Category> pruneCategories(List<Category> categories, String keyword) {
        Iterator<Category> iterator = categories.iterator();
        while (iterator.hasNext()) {
            Category category = iterator.next();
            if (!containsKeyword(category, keyword) && !pruneCategory(category, keyword)) {
                iterator.remove();
            }
        }
        return categories;
    }

    private boolean pruneCategory(Category category, String keyword) {
        if (category.getChildren() != null && !category.getChildren().isEmpty()) {
            Iterator<Category> iterator = category.getChildren().iterator();
            while (iterator.hasNext()) {
                Category subCategory = iterator.next();
                if (!containsKeyword(subCategory, keyword) && !pruneCategory(subCategory, keyword))
                    iterator.remove();
            }
            return !category.getChildren().isEmpty();
        }
        return containsKeyword(category, keyword);
    }

    private boolean containsKeyword(Category category, String keyword) {
        return category.getName().toLowerCase().contains(keyword.toLowerCase());
    }

    public void delete(Long id) {
        List<Category> allList = categoryRepository.findAll();
        Map<Long, List<Category>> parentIdListMap = allList.stream().collect(Collectors.groupingBy(Category::getParentId));
        List<Category> childrenList = findAllChildren(parentIdListMap, id, new ArrayList<>());
        List<Long> idList = new ArrayList<>(childrenList.stream().map(Category::getId).toList());
        idList.add(id);
        for (Long subId: idList) {
            categoryRepository.deleteById(subId);
        }
    }

    private List<Category> findAllChildren(Map<Long, List<Category>> map, Long id, List<Category> targetList) {
        if (map.containsKey(id)) {
            List<Category> childrenList = map.get(id);
            targetList.addAll(childrenList);
            for (Category category : childrenList) {
                targetList.addAll(findAllChildren(map, category.getId(), new ArrayList<>()));
            }
        }
        return targetList;
    }

    public List<Category> findAllLeafs() {
        return categoryRepository.findAllLeafs();
    }

    public void saveAll(List<Category> list) {
        categoryRepository.saveAll(list);
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    private void setChildren(Map<Long, List<Category>> parentListMap, Category parent) {
        if (parentListMap.containsKey(parent.getId())) {
            List<Category> children = parentListMap.get(parent.getId());
            parent.setChildren(children);
            for (Category category: children) {
                setChildren(parentListMap, category);
            }
        }
    }
}
