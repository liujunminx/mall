package com.cloud.product.repository;

import com.cloud.product.entity.SpecValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecValueRepository extends JpaRepository<SpecValue, Long> {
}
