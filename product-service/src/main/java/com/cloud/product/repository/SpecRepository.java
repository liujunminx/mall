package com.cloud.product.repository;

import com.cloud.product.entity.SpecGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecRepository extends JpaRepository<SpecGroup, Long> {
}
