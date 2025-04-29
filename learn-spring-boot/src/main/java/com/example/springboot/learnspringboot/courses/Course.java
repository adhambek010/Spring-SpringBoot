package com.example.springboot.learnspringboot.courses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class Course {
    private long id;
    private String name;
    private String author;
}
