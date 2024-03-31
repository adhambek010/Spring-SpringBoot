package com.rest.webservices.restfulwebservices.versioning;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class PersonV1 implements Person{
    private String name;
}
