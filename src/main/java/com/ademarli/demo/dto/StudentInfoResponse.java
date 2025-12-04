package com.ademarli.demo.dto;

import java.util.List;

/**
 * Öğrenci bilgisi + dersleri bir arada dönen DTO
 */
public class StudentInfoResponse {
    public UserResponse student;
    public List<CourseResponse> courses;

    public StudentInfoResponse() {}

    public StudentInfoResponse(UserResponse student, List<CourseResponse> courses) {
        this.student = student;
        this.courses = courses;
    }
}

