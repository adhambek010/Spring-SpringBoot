package com.example.springboot.learnspringboot.controller;

import com.example.springboot.learnspringboot.courses.Course;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {
    // courses
    // Course: id, name, author
    @RequestMapping("/courses")
    public List<Course> retrieveAllCourses(){
        return Arrays.asList(
                new Course(1, "Learn AWS", "Naveen Reddy"),
                new Course(2, "Learn Spring", "Adkham"),
                new Course(3, "Learn DevOps", "in28minutes")
        );
    }
}
