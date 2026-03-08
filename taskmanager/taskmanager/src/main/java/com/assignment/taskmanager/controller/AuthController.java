package com.assignment.taskmanager.controller;

import com.assignment.taskmanager.dto.AuthResponse;
import com.assignment.taskmanager.dto.LoginRequest;
import com.assignment.taskmanager.dto.RegisterRequest;
import com.assignment.taskmanager.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // Register User
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return "User registered successfully";
    }

    // Login User
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}