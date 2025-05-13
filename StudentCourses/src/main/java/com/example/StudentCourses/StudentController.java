package com.example.StudentCourses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    StudentRepository repo;

    @Autowired
    StudentService studentService;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    @GetMapping
        public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = repo.findAll();
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else{
            return ResponseEntity.ok(students);
        }
    }


}
