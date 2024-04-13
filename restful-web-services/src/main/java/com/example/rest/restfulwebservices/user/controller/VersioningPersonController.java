package com.example.rest.restfulwebservices.user.controller;

import com.example.rest.restfulwebservices.user.User;
import com.example.rest.restfulwebservices.user.UserDaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class VersioningPersonController {
    private final UserDaoService service;

    @GetMapping("/entity/{id}")
    public EntityModel<User> getPersonV1(@PathVariable Integer id) {
        var user = service.findById(id);
        var model = EntityModel.of(user);
        var link = linkTo(methodOn(this.getClass()).retrieveAllV1());
        return model.add(link.withRel("all-users"));
    }

    @GetMapping("/v2/{id}")
    PersonV2 getPersonV2(@PathVariable Integer id) {
        var user = service.findById(id);
        String[] name = user.getName().split(" ");
        return new PersonV2(user.getId(), new Name(name[0], name[1]),user.getBirthDate());
    }

    @GetMapping("/v2")
    List<PersonV2> retrieveAllV2() {
        return getPersonV2s();
    }

    @GetMapping("/v1")
    public List<User> retrieveAllV1() {
        return service.findAll();
    }

    @GetMapping(path = "/person", params = "v3")
    List<PersonV3> retrieveAllV3() {
        return getPersonV3s();
    }
    @GetMapping(path = "/header", headers = "X-API-VERSION=1")
    public List<User> retrieveAllV1Header() {
        return service.findAll();
    }
    @GetMapping(path = "header", headers = "X-API-VERSION=2")
    List<PersonV2> retrieveAllV2Header() {
        return getPersonV2s();
    }

    @GetMapping(path = "/header", headers = "X-API-VERSION=3")
    List<PersonV3> retrieveAllV3Header() {
        return getPersonV3s();
    }

    private List<PersonV2> getPersonV2s() {
        List<User> users = service.findAll();
        List<PersonV2> list = new ArrayList<>();
        for (User user : users) {
            String[] name = user.getName().split(" ");
            list.add(new PersonV2(user.getId(), new Name(name[0], name[1]),user.getBirthDate()));
        }
        return list;
    }

    private List<PersonV3> getPersonV3s() {
        List<User> users = service.findAll();
        List<PersonV3> list = new ArrayList<>();
        for (User user : users) {
            String[] name = user.getName().split(" ");
            list.add(new PersonV3(user.getId(), name[0], name[1],user.getBirthDate()));
        }
        return list;
    }

}
record PersonV2(Integer id, Name name, LocalDate birthDate) {}
record PersonV3(Integer id, String firstName, String lastName, LocalDate birthDate) {}
record Name(String firstName, String lastName) {}