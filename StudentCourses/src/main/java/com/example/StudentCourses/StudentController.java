package com.example.StudentCourses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student newStudent = repo.save(student);
        return ResponseEntity.ok(newStudent);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        repo.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<Student> updateStu (@RequestBody Student student) throws Exception {
        Student s = studentService.updateStudent(student);
        if (s == null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.accepted().body(s);

        }

    }
}

