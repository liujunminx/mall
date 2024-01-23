package com.cloud.product.repository;

import com.cloud.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    @Query("SELECT pc FROM ProductCategory pc WHERE NOT EXISTS ( SELECT 1 FROM ProductCategory child WHERE child.parentId = pc.id)")
    List<ProductCategory> findAllLeafs();
}
