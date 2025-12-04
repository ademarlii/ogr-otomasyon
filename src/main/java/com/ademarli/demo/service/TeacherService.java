package com.ademarli.demo.service;

import com.ademarli.demo.dto.CourseCreateRequest;
import com.ademarli.demo.dto.CourseResponse;

import java.util.List;

public interface TeacherService {
    CourseResponse createCourse(CourseCreateRequest request);
    CourseResponse updateCourse(Long courseId, CourseCreateRequest request);
    void deleteCourse(Long courseId);
    List<CourseResponse> getCoursesByTeacherId(Long teacherId);
}

