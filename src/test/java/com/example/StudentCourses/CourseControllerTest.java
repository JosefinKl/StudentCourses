package com.example.StudentCourses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

//Component test
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class CourseControllerTest {
    //Mocked Repo
    StudentRepository studentRepository = mock(StudentRepository.class);
    CourseRepository courseRepository = mock(CourseRepository.class);

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addCourse() throws Exception {
        //Arrange
        Course newCourse = new Course();
        newCourse.setCourseName("Test");


        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCourse)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseName", is("Test")));

    }


    @Test
    void getAllCourses() throws Exception {
        // Arrange
        Course newCourse = new Course();
        newCourse.setCourseName("Test");

        // Act and Assert for POST
        mockMvc.perform(MockMvcRequestBuilders.post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCourse)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseName", is("Test")));

        // Act and Assert for GET
        mockMvc.perform(MockMvcRequestBuilders.get("/courses"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].courseName", is("Test")));
    }
}