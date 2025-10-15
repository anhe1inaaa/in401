package handler;

import entity.Course;
import org.springframework.stereotype.Component;

import java.util.List;

// handler/FacultativeHandler.java
@Component
public class FacultativeHandler {
    public List<Course> getSampleCourses() {
        return List.of(
                new Course(1L, "Math", "Prof. Ivanenko"),
                new Course(2L, "Physics", "Dr. Petrenko")
        );
    }
}
