package ua.com.reactive.kovalova.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.reactive.kovalova.entity.Course;
import ua.com.reactive.kovalova.entity.Enrollment;
import ua.com.reactive.kovalova.entity.Grade;
import ua.com.reactive.kovalova.entity.Student;

import java.util.List;

@RequestMapping("/api")
@RestController
public class FacultativeController {

    @GetMapping("/")
    public String getHello() {
        return "hello world";
    }

    @GetMapping("/courses")
    public List<Course> getCourses() {
        return List.of(
                new Course(1L, "Math", "Prof. Ivanenko"),
                new Course(2L, "Physics", "Dr. Petrenko")
        );
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return List.of(
                new Student(1L, "Andrii"),
                new Student(2L, "Olena")
        );
    }

    @GetMapping("/enrollments")
    public List<Enrollment> getEnrollments() {
        return List.of(
                new Enrollment(1L, 1L, 1L),
                new Enrollment(2L, 2L, 2L)
        );
    }

    @GetMapping("/grades")
    public List<Grade> getGrades() {
        return List.of(
                new Grade(1L, 1L, 95),
                new Grade(2L, 2L, 88)
        );
    }
}


