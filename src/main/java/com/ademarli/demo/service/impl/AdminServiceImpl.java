package com.ademarli.demo.service.impl;

import com.ademarli.demo.entity.Course;
import com.ademarli.demo.entity.Role;
import com.ademarli.demo.entity.User;
import com.ademarli.demo.repository.CourseRepository;
import com.ademarli.demo.repository.UserRepository;
import com.ademarli.demo.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public AdminServiceImpl(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> listUsersByRole(Role role) {
        return userRepository.findAll().stream()
                .filter(u -> u.getRole() == role)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<Course> listAllCourses() {
        return courseRepository.findAll();
    }
}

