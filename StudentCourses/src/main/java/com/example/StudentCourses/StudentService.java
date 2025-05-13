package com.example.StudentCourses;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @PersistenceContext
    private EntityManager entityManager;

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.getReferenceById(id);
    }
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
    public Student updateStudent(Student student) {
        return studentRepository.findById(student.getId()).map(Students ->{
            Students.setFirstName(student.getFirstName());
            Students.setLastName(student.getLastName());
            return studentRepository.save(Students);
        }).orElse(null);
    }
}
