package com.example.restfulapi.helloworld;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HelloWorldBean {

    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean [message=" + message + "]";
    }
}
