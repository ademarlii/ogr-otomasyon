package com.ademarli.demo.dto;

import com.ademarli.demo.entity.Role;
import lombok.Data;

/**
 * Kayıt isteği DTO'su
 */
@Data
public class RegisterRequest {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public Role role;

    // default constructor
    public RegisterRequest() {}

    public RegisterRequest(String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}

