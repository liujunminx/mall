package com.cloud.product.repository;

import com.cloud.product.dto.AttributePageDto;
import com.cloud.product.entity.Attribute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long>, JpaSpecificationExecutor<Attribute> {

    @Query("SELECT new com.cloud.product.dto.AttributePageDto(a.id, a.name, a.value, c.name, s.name) " +
            "FROM Attribute a " +
            "JOIN a.spec s " +
            "JOIN a.category c " +
            "WHERE (:keyword is null or a.name LIKE %:keyword%)")
    Page<AttributePageDto> findAllAttributes(String keyword, Pageable pageable);
}
