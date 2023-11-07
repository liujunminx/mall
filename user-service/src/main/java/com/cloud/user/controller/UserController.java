package com.cloud.user.controller;

import com.cloud.user.entity.User;
import com.cloud.user.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.PushBuilder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/listPage")
    public ResponseEntity<Page<User>> listPage() {
        Page<User> page = userService.listPage();
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
