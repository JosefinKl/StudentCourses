package com.example.StudentCourses;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @PersistenceContext
    private EntityManager entityManager;

    CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course getCourse(int id) {
        return courseRepository.getReferenceById(id);
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

}
