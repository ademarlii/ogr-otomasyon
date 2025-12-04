package com.ademarli.demo.service.impl;

import com.ademarli.demo.dto.CourseCreateRequest;
import com.ademarli.demo.dto.CourseResponse;
import com.ademarli.demo.entity.Course;
import com.ademarli.demo.entity.Role;
import com.ademarli.demo.entity.User;
import com.ademarli.demo.repository.CourseRepository;
import com.ademarli.demo.repository.UserRepository;
import com.ademarli.demo.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public TeacherServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CourseResponse createCourse(CourseCreateRequest request) {
        User teacher = userRepository.findById(request.teacherId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Teacher not found"));
        if (teacher.getRole() != Role.TEACHER) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not a teacher");
        }
        Course course = new Course(request.name, request.code, request.description, teacher);
        Course saved = courseRepository.save(course);
        return toResponse(saved);
    }

    @Override
    public CourseResponse updateCourse(Long courseId, CourseCreateRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
        course.setName(request.name);
        course.setCode(request.code);
        course.setDescription(request.description);
        if (request.teacherId != null) {
            User teacher = userRepository.findById(request.teacherId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Teacher not found"));
            if (teacher.getRole() != Role.TEACHER) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not a teacher");
            }
            course.setTeacher(teacher);
        }
        Course saved = courseRepository.save(course);
        return toResponse(saved);
    }

    @Override
    public void deleteCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
        }
        courseRepository.deleteById(courseId);
    }

    @Override
    public List<CourseResponse> getCoursesByTeacherId(Long teacherId) {
        List<Course> courses = courseRepository.findByTeacherId(teacherId);
        return courses.stream().map(this::toResponse).collect(Collectors.toList());
    }

    private CourseResponse toResponse(Course course) {
        String teacherName = null;
        Long teacherId = null;
        if (course.getTeacher() != null) {
            teacherId = course.getTeacher().getId();
            teacherName = course.getTeacher().getFirstName() + " " + course.getTeacher().getLastName();
        }
        return new CourseResponse(course.getId(), course.getName(), course.getCode(), course.getDescription(), teacherId, teacherName);
    }
}

