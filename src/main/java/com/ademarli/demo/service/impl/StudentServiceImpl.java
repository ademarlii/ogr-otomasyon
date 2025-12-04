package com.ademarli.demo.service.impl;

import com.ademarli.demo.dto.CourseResponse;
import com.ademarli.demo.dto.EnrollmentRequest;
import com.ademarli.demo.entity.Course;
import com.ademarli.demo.entity.Role;
import com.ademarli.demo.entity.StudentCourseEnrollment;
import com.ademarli.demo.entity.User;
import com.ademarli.demo.repository.CourseRepository;
import com.ademarli.demo.repository.StudentCourseEnrollmentRepository;
import com.ademarli.demo.repository.UserRepository;
import com.ademarli.demo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentCourseEnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentCourseEnrollmentRepository enrollmentRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseResponse enrollStudent(EnrollmentRequest request) {
        User student = userRepository.findById(request.studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student not found"));
        if (student.getRole() != Role.STUDENT) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not a student");
        }
        Course course = courseRepository.findById(request.courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course not found"));

        StudentCourseEnrollment enrollment = new StudentCourseEnrollment(student, course, LocalDateTime.now());
        StudentCourseEnrollment saved = enrollmentRepository.save(enrollment);
        return toCourseResponse(saved.getCourse());
    }

    @Override
    public List<CourseResponse> getCoursesOfStudent(Long studentId) {
        List<StudentCourseEnrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        return enrollments.stream()
                .map(StudentCourseEnrollment::getCourse)
                .map(this::toCourseResponse)
                .collect(Collectors.toList());
    }

    private CourseResponse toCourseResponse(Course course) {
        Long teacherId = null;
        String teacherName = null;
        if (course.getTeacher() != null) {
            teacherId = course.getTeacher().getId();
            teacherName = course.getTeacher().getFirstName() + " " + course.getTeacher().getLastName();
        }
        return new CourseResponse(course.getId(), course.getName(), course.getCode(), course.getDescription(), teacherId, teacherName);
    }
}

