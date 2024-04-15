package com.cloud.good.repository;

import com.cloud.good.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT pc FROM Category pc WHERE NOT EXISTS ( SELECT 1 FROM Category child WHERE child.parentId = pc.id)")
    List<Category> findAllLeafs();
}
