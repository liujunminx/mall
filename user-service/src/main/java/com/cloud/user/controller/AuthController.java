package com.cloud.user.controller;

import com.cloud.user.dto.UserSignUpDto;
import com.cloud.user.entity.User;
import com.cloud.user.exception.ConflictException;
import com.cloud.user.exception.UnAuthorizedException;
import com.cloud.user.service.AuthService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {

    @Resource
    private AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserSignUpDto user) {
        if (authService.exist(user.username()))
            throw new ConflictException("Username already exists");
        authService.signUp(user);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody UserSignUpDto user) {
        if (authService.signIn(user))
            return new ResponseEntity<>("OK", HttpStatus.OK);
        else
            throw new UnAuthorizedException("Username or password incorrectly");
    }
}
