package com.ademarli.demo.service;

import com.ademarli.demo.dto.LoginRequest;
import com.ademarli.demo.dto.RegisterRequest;
import com.ademarli.demo.dto.UserResponse;

public interface AuthService {
    UserResponse register(RegisterRequest request);
    UserResponse login(LoginRequest request);
}

