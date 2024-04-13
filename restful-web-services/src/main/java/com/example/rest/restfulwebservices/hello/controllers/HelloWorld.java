package com.example.rest.restfulwebservices.hello.controllers;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@NoArgsConstructor
public class HelloWorld {
    private String name;
    public HelloWorld(String helloWorld) {
        this.name = helloWorld;
    }
}
