package com.cloud.user.controller;

import com.cloud.user.dto.UserSignUpDto;
import com.cloud.user.exception.ConflictException;
import com.cloud.user.exception.UnAuthorizedException;
import com.cloud.user.service.UserService;
import com.cloud.user.util.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {

    @Resource
    private UserService userService;

    @Resource
    private JwtUtil jwtTokenUtil;

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserSignUpDto user) {
        if (userService.exist(user.username()))
            throw new ConflictException("Username already exists");
        userService.signUp(user);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody UserSignUpDto user, HttpServletResponse response) {
        if (userService.authenticate(user)){
            String token = jwtTokenUtil.generateToken(user.username());
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60*60*7);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return ResponseEntity.ok(token);
        }
        else
            throw new UnAuthorizedException("Username or password incorrectly");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Boolean> authenticate(@RequestBody String token) {
        boolean authenticate = jwtTokenUtil.authenticate(token);
        return ResponseEntity.ok(authenticate);
    }
}
