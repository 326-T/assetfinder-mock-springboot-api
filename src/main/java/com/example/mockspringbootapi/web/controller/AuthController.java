package com.example.mockspringbootapi.web.controller;

import com.example.mockspringbootapi.annotation.NonAuth;
import com.example.mockspringbootapi.web.response.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private String AUTH_TOKEN;
    public AuthController(@Value("${constants.auth-token}")String token) {
        AUTH_TOKEN = token;
    }

    @GetMapping("/current-user")
    public UserResponse currentUser() {
        return UserResponse.builder().id(1).name("privilege").email("xxx@gmail.com").token(AUTH_TOKEN).build();
    }

    @NonAuth
    @PostMapping("/sign-in")
    public UserResponse login() {
        return UserResponse.builder().id(1).name("privilege").email("xxx@gmail.com").token(AUTH_TOKEN).build();
    }
}
