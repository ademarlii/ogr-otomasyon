package com.ademarli.demo.dto;

/**
 * Ders oluşturma isteği
 */
public class CourseCreateRequest {
    public String name;
    public String code;
    public String description;
    public Long teacherId;

    public CourseCreateRequest() {}

    public CourseCreateRequest(String name, String code, String description, Long teacherId) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.teacherId = teacherId;
    }
}

