package com.example.spring.security.springsecurity.resources;

import jakarta.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoResource {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private final List<Todo> todos = List.of(
            new Todo("user", "Learn AWS"),
            new Todo("user", "Get AWS Certified")

    );

    @GetMapping("/todos")
    public List<?> retrieveAllTodos() {
        return todos;
    }

    @GetMapping("/users/{username}/todos")
//    @PreAuthorize("hasRole('USER') and #username == authentication.name")
    @PostAuthorize("returnObject.username == 'user'")
//    @RolesAllowed({"ADMIN", "USER"})
//    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Todo retrieveTodosForSpecificUser(@PathVariable String username){
        return todos.get(0);
    }

    @PostMapping("users/{username}/todos")
    public void addTodosForSpecificUser(@PathVariable String username, @RequestBody Todo todo){
        logger.info("Creating {} for {}", todo, username);
    }
}

record Todo(String username, String description) {}