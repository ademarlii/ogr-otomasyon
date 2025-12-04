package com.ademarli.demo.controller;

import com.ademarli.demo.dto.CourseCreateRequest;
import com.ademarli.demo.dto.CourseResponse;
import com.ademarli.demo.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Öğretmen işlemleri: ders oluşturma, listeleme, güncelleme, silme
 */
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseCreateRequest request) {
        CourseResponse response = teacherService.createCourse(request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{teacherId}/courses")
    public ResponseEntity<List<CourseResponse>> getCoursesByTeacher(@PathVariable Long teacherId) {
        List<CourseResponse> courses = teacherService.getCoursesByTeacherId(teacherId);
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/courses/{courseId}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long courseId, @RequestBody CourseCreateRequest request) {
        CourseResponse updated = teacherService.updateCourse(courseId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        teacherService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }
}

