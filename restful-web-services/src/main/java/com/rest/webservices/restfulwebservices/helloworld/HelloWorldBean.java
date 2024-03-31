package com.rest.webservices.restfulwebservices.helloworld;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloWorldBean {
    private String message;
    // messages
    public HelloWorldBean(String message) {
        this.message = message;
    }
}
