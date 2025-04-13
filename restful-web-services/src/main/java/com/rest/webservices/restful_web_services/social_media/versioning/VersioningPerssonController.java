package com.rest.webservices.restful_web_services.social_media.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPerssonController {

    @GetMapping("/v1/person")
     public PersonV1 getPersonV1(){
         return new PersonV1("Bob Backer");
     }

     @GetMapping("/v2/person")
     PersonV2 getPersonV2(){
         return new PersonV2(new Name("Bob", "Backer"));
     }

     @GetMapping(path = "/person", params = "version=1")
     public PersonV1 getPersonV1Version1RequestParam(){
        return new PersonV1("Bob Backer");
     }

    @GetMapping(path = "/person", params = "version=2")
    PersonV2 getPersonV2Version2RequestParam(){
        return new PersonV2(new Name("Bob", "Backer"));
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getPersonV1Version1Header(){
        return new PersonV1("Bob Backer");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    PersonV2 getPersonV2Version2Header(){
        return new PersonV2(new Name("Bob", "Backer"));
    }




}
