package com.example.StudentCourses;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ActiveProfiles("test")
class CourseServiceTest {

    //Mock repository. Tests without database and Spring.
    CourseRepository mockedCourseRepo= mock(CourseRepository.class);

    CourseService courseService = new CourseService(mockedCourseRepo);

    @Test
    public void testToAddNewCourse(){
        //Arrange
        Course course = new Course();
        course.setCourseName("Test");
        //Act
        Course c = courseService.addCourse(course);
        assertNotNull(c);
    }

    @Test
    public void testGetAllCourses(){
        //Arrange
        Course course = new Course();
        course.setCourseName("Test");
        //Act
        Course c = courseService.addCourse(course);
        //Assert
        assertNotNull(c);
    }

}