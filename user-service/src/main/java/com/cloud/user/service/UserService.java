package com.cloud.user.service;

import com.cloud.user.dto.UserSignUpDto;
import com.cloud.user.entity.User;
import com.cloud.user.exception.InternalServerErrorException;
import com.cloud.user.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
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

    public void signUp(UserSignUpDto userDto) {
        String salt = generateSalt();
        String hashPassword = hashPassword(userDto.password(), salt);
        User user = User.builder().username(userDto.username()).password(hashPassword).salt(salt).build();
        userRepository.save(user);
    }

    public boolean authenticate(UserSignUpDto userDto) {
        User user = userRepository.findByUsername(userDto.username());
        if (user == null)
            return false;
        String salt = user.getSalt();
        String hashPassword = hashPassword(userDto.password(), salt);
        return hashPassword.equals(user.getPassword());
    }

    public boolean exist(String username) {
        return userRepository.countAllByUsername(username) > 0;
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashPassword(String password, String salt) {
        String passwordWithSalt = password + salt;
        String algorithm = "SHA-256";
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] hashPassword = md.digest(passwordWithSalt.getBytes());
            return Base64.getEncoder().encodeToString(hashPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new InternalServerErrorException("Valid password error");
        }
    }
}
