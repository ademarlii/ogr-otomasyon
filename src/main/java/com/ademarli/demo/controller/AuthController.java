package com.ademarli.demo.controller;


import com.ademarli.demo.dto.LoginRequest;
import com.ademarli.demo.dto.RegisterRequest;
import com.ademarli.demo.dto.UserResponse;
import com.ademarli.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Basit auth controller: register ve login işlemleri (gerçek security yok)
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        UserResponse response = authService.register(request);
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request) {
        UserResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}

