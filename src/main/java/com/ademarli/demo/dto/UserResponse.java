package com.ademarli.demo.dto;

import com.ademarli.demo.entity.Role;
import lombok.Data;

/**
 * Kullanıcı yanıt DTO'su
 */
@Data
public class UserResponse {
    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public Role role;

    public UserResponse() {}

    public UserResponse(Long id, String firstName, String lastName, String email, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}

