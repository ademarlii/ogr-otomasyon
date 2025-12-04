package com.ademarli.demo.dto;

/**
 * Öğrenci kaydı isteği
 */
public class EnrollmentRequest {
    public Long studentId;
    public Long courseId;

    public EnrollmentRequest() {}

    public EnrollmentRequest(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
}

