package com.cloud.product.repository;

import com.cloud.product.entity.SpecGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecGroupRepository extends JpaRepository<SpecGroup, Long>, JpaSpecificationExecutor<SpecGroup> {

    Page<SpecGroup> findByNameContaining(String name, Pageable pageable);
}
