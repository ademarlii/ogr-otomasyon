package com.ademarli.demo.controller;

import com.ademarli.demo.dto.CourseResponse;
import com.ademarli.demo.dto.EnrollmentRequest;
import com.ademarli.demo.dto.StudentInfoResponse;
import com.ademarli.demo.dto.UserResponse;
import com.ademarli.demo.entity.User;
import com.ademarli.demo.service.StudentService;
import com.ademarli.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Öğrenci işlemleri: kayıt (enroll), öğrenci dersleri ve öğrenci bilgisi
 */
@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;
    private final UserRepository userRepository;

    public StudentController(StudentService studentService, UserRepository userRepository) {
        this.studentService = studentService;
        this.userRepository = userRepository;
    }

    @PostMapping("/enroll")
    public ResponseEntity<CourseResponse> enroll(@RequestBody EnrollmentRequest request) {
        CourseResponse response = studentService.enrollStudent(request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<CourseResponse>> getStudentCourses(@PathVariable Long studentId) {
        List<CourseResponse> courses = studentService.getCoursesOfStudent(studentId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{studentId}/info")
    public ResponseEntity<StudentInfoResponse> getStudentInfo(@PathVariable Long studentId) {
        User user = userRepository.findById(studentId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserResponse ur = new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole());
        List<CourseResponse> courses = studentService.getCoursesOfStudent(studentId);
        StudentInfoResponse info = new StudentInfoResponse(ur, courses);
        return ResponseEntity.ok(info);
    }
}
