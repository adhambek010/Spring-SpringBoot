package com.rest.webservices.restfulwebservices.versioning;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PersonV2 implements Person {
    private Name name;

}
