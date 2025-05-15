package com.example.StudentCourses;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class StudentServiceTest {

    //Mock repository. Tests without database and Spring.
    StudentRepository mockedRepo =mock(StudentRepository.class);
    CourseRepository mockedCourseRepo= mock(CourseRepository.class);

    StudentService studentService = new StudentService(mockedRepo, mockedCourseRepo);



    @Test
    void getAllStudentsShouldReturnAllStudents() {
        //arrange
        Student s1 = new Student("John", "Smith");
        Student s2 = new Student("Jane", "Smith");
        List<Student> students = List.of(s1,s2);

        when(mockedRepo.findAll()).thenReturn(students);

        //act
        List<Student> result = studentService.getAllStudents();

        //assert
        assertNotNull(result);
        assertEquals(students.size(), result.size());
        assertEquals(students, result);
    }

    //Test to update a student when the student does not exist
    @Test
    void testToUpdateStudentWithNonExistingStudentShouldReturnNull() {
        //Arrange
        Student s1 = new Student("John", "Smith");

        //Act
        Student updateStudent = studentService.updateStudent(s1);
       //Assert
        assertNull(updateStudent);
    }

    @Test
    void testToConvertStudentToStudentDTO(){
        //Arrange
        Student s1 = new Student("John", "Smith");
        s1.setId(1);
        Course c1 = new Course();
        c1.setId(101);
        s1.setCourses(Set.of(c1));

        StudentDTO expected1DTO = new StudentDTO();
        expected1DTO.setId(1);
        expected1DTO.setFirstName("John");
        expected1DTO.setLastName("Smith");
        expected1DTO.setCourseIds(Set.of(101));

        //Act
        StudentDTO actualDTO = studentService.convertToDTO(s1);

        //Assert
        assertEquals(expected1DTO.getId(), actualDTO.getId());
        assertEquals(expected1DTO.getFirstName(), actualDTO.getFirstName());
        assertEquals(expected1DTO.getLastName(), actualDTO.getLastName());
        assertEquals(expected1DTO.getCourseIds(), actualDTO.getCourseIds());

    }



}