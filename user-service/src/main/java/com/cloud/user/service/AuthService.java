package com.cloud.user.service;

import com.cloud.user.dto.UserSignUpDto;
import com.cloud.user.entity.User;
import com.cloud.user.repository.UserRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
@Slf4j
public class AuthService {

    @Resource
    private UserRepository userRepository;

    public void signUp(UserSignUpDto userDto) {
        String salt = generateSalt();
        String hashPassword = hashPassword(userDto.password(), salt);
        User user = User.builder().username(userDto.username()).password(hashPassword).salt(salt).build();
        userRepository.save(user);
    }

    public boolean signIn(UserSignUpDto userDto) {
        User existUser = userRepository.findByUsername(userDto.username());
        if (existUser == null)
            return true;
        String salt = existUser.getSalt();
        String hashPassword = hashPassword(userDto.password(), salt);
        return hashPassword.equals(existUser.getPassword());
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
            log.error("Not found Algorithm: {}", algorithm);
            throw new RuntimeException(e);
        }
    }
}
