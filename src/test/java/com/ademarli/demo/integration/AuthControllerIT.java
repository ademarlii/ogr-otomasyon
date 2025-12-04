package com.ademarli.demo.integration;

import com.ademarli.demo.controller.AuthController;
import com.ademarli.demo.dto.RegisterRequest;
import com.ademarli.demo.dto.UserResponse;
import com.ademarli.demo.entity.Role;
import com.ademarli.demo.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AuthController.class)
public class AuthControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthService authService;

    @Test
    void register_success_returnsCreated() throws Exception {
        RegisterRequest req = new RegisterRequest("Test", "User", "t@test.com", "pass", Role.STUDENT);
        UserResponse resp = new UserResponse(10L, "Test", "User", "t@test.com", Role.STUDENT);

        when(authService.register(any(RegisterRequest.class))).thenReturn(resp);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(resp)));
    }

    @Test
    void register_emailExists_returnsBadRequest() throws Exception {
        RegisterRequest req = new RegisterRequest("Test", "User", "exists@test.com", "pass", Role.STUDENT);
        when(authService.register(any(RegisterRequest.class)))
                .thenThrow(new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "Email already registered"));

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest());
    }


}
