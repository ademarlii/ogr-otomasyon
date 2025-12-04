package com.ademarli.demo.birim;

import com.ademarli.demo.dto.RegisterRequest;
import com.ademarli.demo.dto.UserResponse;
import com.ademarli.demo.entity.Role;
import com.ademarli.demo.entity.User;
import com.ademarli.demo.repository.UserRepository;
import com.ademarli.demo.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    AuthServiceImpl service;

    @Test
    void createStudent() {

        RegisterRequest registerRequest=new RegisterRequest();
        registerRequest.setFirstName("Adem");
        registerRequest.setLastName("Arlı");
        registerRequest.setEmail("adem@gmail.com");
        registerRequest.setPassword("12345");
        registerRequest.setRole(Role.STUDENT);

        User user=new User();
        user.setId(1L);
        user.setEmail("adem@gmail.com");
        user.setFirstName("Adem");
        user.setLastName("Arlı");
        user.setPassword("12345");
        user.setRole(Role.STUDENT);




        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);


        UserResponse createdUser=service.register(registerRequest);


        assertEquals(1L, (long) createdUser.getId());
        assertEquals("Adem", createdUser.getFirstName());
        assertEquals("Arlı", createdUser.getLastName());


    }
}
