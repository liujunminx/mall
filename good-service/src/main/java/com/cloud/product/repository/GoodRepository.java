package com.cloud.product.repository;

import com.cloud.product.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodRepository extends JpaRepository<Good, Long> {

}
