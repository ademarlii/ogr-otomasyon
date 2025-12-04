package com.ademarli.demo.service;

import com.ademarli.demo.dto.CourseResponse;
import com.ademarli.demo.dto.EnrollmentRequest;

import java.util.List;

public interface StudentService {
    CourseResponse enrollStudent(EnrollmentRequest request);
    List<CourseResponse> getCoursesOfStudent(Long studentId);
}

