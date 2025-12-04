package com.ademarli.demo.dto;

/**
 * Ders yanıt DTO'sı
 */
public class CourseResponse {
    public Long id;
    public String name;
    public String code;
    public String description;
    public Long teacherId;
    public String teacherName;

    public CourseResponse() {}

    public CourseResponse(Long id, String name, String code, String description, Long teacherId, String teacherName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }
}

