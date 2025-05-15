package com.example.StudentCourses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/students")
public class StudentController {

    StudentRepository repo;
    CourseService courseService;


    @Autowired
    StudentService studentService;


    public StudentController(StudentRepository repo, CourseService courseService) {
        this.repo = repo;
        this.courseService = courseService;
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
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Integer id){

        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
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

    @PostMapping("/{studentId}/courses")
    public ResponseEntity<StudentDTO> addCourseToStudents(@PathVariable Integer studentId, @RequestBody List<Integer> courseIds){
        return ResponseEntity.ok(studentService.addCoursesToStudent(studentId,courseIds));
    }

//    @PostMapping("/course/{studentId}/{courseId}")
//    public ResponseEntity<Student> addCourse(@PathVariable int studentId, @PathVariable int courseId) {
//        Student s = repo.findById(studentId).get();
//        Course c = courseService.getCourse(courseId);
//        c.setStudent(s);
//        if( c != null ){
//            return ResponseEntity.accepted().body(s);
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//
//    }
}

