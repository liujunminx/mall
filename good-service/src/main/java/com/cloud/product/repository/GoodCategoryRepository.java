package com.cloud.product.repository;

import com.cloud.product.entity.GoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodCategoryRepository extends JpaRepository<GoodCategory, Long> {

    @Query("SELECT pc FROM GoodCategory pc WHERE NOT EXISTS ( SELECT 1 FROM GoodCategory child WHERE child.parentId = pc.id)")
    List<GoodCategory> findAllLeafs();
}
