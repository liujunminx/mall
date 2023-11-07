package com.cloud.user.repository;

import com.cloud.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    int countAllByUsername(String username);

    User findByUsername(String username);
}
