package com.ademarli.demo.controller;

import com.ademarli.demo.dto.CourseResponse;
import com.ademarli.demo.dto.UserResponse;
import com.ademarli.demo.entity.Course;
import com.ademarli.demo.entity.User;
import com.ademarli.demo.entity.Role;
import com.ademarli.demo.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Admin işlemleri: kullanıcı ve ders yönetimi (gerçek security yok)
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> listUsers(@RequestParam(required = false) Role role) {
        List<User> users;
        if (role == null) {
            users = adminService.listAllUsers();
        } else {
            users = adminService.listUsersByRole(role);
        }
        List<UserResponse> resp = users.stream()
                .map(u -> new UserResponse(u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getRole()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseResponse>> listCourses() {
        List<Course> courses = adminService.listAllCourses();
        List<CourseResponse> resp = courses.stream().map(c -> new CourseResponse(c.getId(), c.getName(), c.getCode(), c.getDescription(),
                c.getTeacher() != null ? c.getTeacher().getId() : null,
                c.getTeacher() != null ? c.getTeacher().getFirstName() + " " + c.getTeacher().getLastName() : null)).collect(Collectors.toList());
        return ResponseEntity.ok(resp);
    }
}
