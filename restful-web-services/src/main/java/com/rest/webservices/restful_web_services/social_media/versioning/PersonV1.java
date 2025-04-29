package com.rest.webservices.restful_web_services.social_media.versioning;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonV1 {
    private String name;
}

@Data
@AllArgsConstructor
class PersonV2 {
    private Name name;

}

@Data
@AllArgsConstructor
class Name{
    private String firstName;
    private String lastName;
}