package com.ademarli.demo.repository;

import com.ademarli.demo.entity.StudentCourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentCourseEnrollmentRepository extends JpaRepository<StudentCourseEnrollment, Long> {
    List<StudentCourseEnrollment> findByStudentId(Long studentId);
    List<StudentCourseEnrollment> findByCourseId(Long courseId);
}

