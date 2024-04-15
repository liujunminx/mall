package com.cloud.good.repository;

import com.cloud.good.entity.SpecValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecValueRepository extends JpaRepository<SpecValue, Long> {
}
