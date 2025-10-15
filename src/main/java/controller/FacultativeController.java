package controller;


import entity.Course;
import entity.Enrollment;
import entity.Grade;
import entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FacultativeController {

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

