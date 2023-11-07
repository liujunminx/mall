package com.cloud.user.service;

import com.cloud.user.entity.User;
import com.cloud.user.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    public List<User> list() {
        return userRepository.findAll();
    }

    public Page<User> listPage() {
        return userRepository.findAll(Pageable.ofSize(10));
    }
}
