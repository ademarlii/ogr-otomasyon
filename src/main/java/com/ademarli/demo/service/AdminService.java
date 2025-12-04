package com.ademarli.demo.service;

import com.ademarli.demo.entity.Role;
import com.ademarli.demo.entity.User;

import java.util.List;

public interface AdminService {
    List<User> listAllUsers();
    List<User> listUsersByRole(Role role);
    void deleteUser(Long id);
    List<com.ademarli.demo.entity.Course> listAllCourses();
}
