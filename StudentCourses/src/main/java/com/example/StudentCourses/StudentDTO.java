package com.example.StudentCourses;

import java.util.List;
import java.util.Set;

public class StudentDTO {
    private Integer id;

    private String firstName;

    private String lastName;

    private Set<Integer> courseIds;

    public Set<Integer> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(Set<Integer> courseIds) {
        this.courseIds = courseIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



}
