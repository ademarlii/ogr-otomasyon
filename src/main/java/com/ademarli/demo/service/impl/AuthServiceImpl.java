package com.ademarli.demo.service.impl;

import com.ademarli.demo.dto.LoginRequest;
import com.ademarli.demo.dto.RegisterRequest;
import com.ademarli.demo.dto.UserResponse;
import com.ademarli.demo.entity.User;
import com.ademarli.demo.repository.UserRepository;
import com.ademarli.demo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse register(RegisterRequest request) {

        Optional<User> existing = userRepository.findByEmail(request.email);
        if (existing.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
        }

        User user = new User(request.firstName, request.lastName, request.email, request.password, request.role);
        User saved = userRepository.save(user);
        return toResponse(saved);
    }

    @Override
    public UserResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));
        if (!user.getPassword().equals(request.password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
        return toResponse(user);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole());
    }
}

