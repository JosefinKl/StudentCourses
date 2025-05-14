package com.example.StudentCourses;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//Integration test to test the whole chain, controller, service, repository and mySQL. Uses a test properties file to make sure a test database will be used for the test, hence isolated from production database.
@ActiveProfiles("test") //to use a test database isolated from production database (with create-drop, hence the table and its content will be created at start and dropped at the end)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTestIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateStudentAndGetAllStudents(){
        Student newStudent = new Student("Test", "TestLast");


        ResponseEntity<Student> postResponse = restTemplate.postForEntity("http://localhost:" + port + "/students", newStudent, Student.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode() );

        ResponseEntity<List<Student>> response = restTemplate.exchange(
                "http://localhost:" + port + "/students",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<java.util.List<Student>>() {}
        );

        List<Student> students = response.getBody();
        String firstName = students.get(0).getFirstName();
        String lastName = students.get(0).getLastName();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test", firstName);
        assertEquals("TestLast", lastName);
    }


//    @Test
//    void testDeleteStudentAndGetAllStudents(){
//        Student newStudent = new Student("Test", "TestLast");
//
//    }
}