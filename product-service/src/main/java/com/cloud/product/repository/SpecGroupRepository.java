package com.cloud.product.repository;

import com.cloud.product.entity.SpecGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecGroupRepository extends JpaRepository<SpecGroup, Long>, JpaSpecificationExecutor<SpecGroup> {

    List<SpecGroup> findByCategoryId(Long category);
}
