package com.example.StudentCourses;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final CourseRepository courseRepository;
    @PersistenceContext
    private EntityManager entityManager;

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentDTO getStudentById(int studentId) {
        Optional<Student> student = studentRepository.findById(studentId);

        if (student.isPresent()) {
            Student s = student.get();
            StudentDTO studentDTO = convertToDTO(s);
            return studentDTO;
        }
        return null;
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

    public StudentDTO addCoursesToStudent(Integer studentId, List<Integer> coursesId) {
        Optional<Student> student = studentRepository.findById(studentId);

        if(student.isPresent()) {
            Student s = student.get();
            Set<Course> courses = courseRepository.findAllById(coursesId).stream().collect(Collectors.toSet());
            s.setCourses(courses);
            studentRepository.save(s);
            for (Course course : courses) {
                course.getStudents().add(s);
            }

            StudentDTO studentDTO = convertToDTO(s);
            return studentDTO;
        }
        return null;    }

    public StudentDTO convertToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());

        Set<Integer> courseIds = student.getCourses().stream()
                .map(Course::getId)
                .collect(Collectors.toSet());
        dto.setCourseIds(courseIds);
        return dto;
    }

}
