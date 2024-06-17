package com.cloud.product.repository;

import com.cloud.product.entity.Spec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecRepository extends JpaRepository<Spec, Long>, JpaSpecificationExecutor<Spec> {

    List<Spec> findByCategoryId(Long category);
}
