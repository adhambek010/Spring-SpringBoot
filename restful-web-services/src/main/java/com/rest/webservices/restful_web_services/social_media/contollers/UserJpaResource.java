package com.rest.webservices.restful_web_services.social_media.contollers;

import com.rest.webservices.restful_web_services.social_media.dao.UserRepository;
import com.rest.webservices.restful_web_services.social_media.exception.UserNotFoundException;
import com.rest.webservices.restful_web_services.social_media.models.Post;
import com.rest.webservices.restful_web_services.social_media.models.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/users/posts")
    public List<Post> retrieveAllPosts() {
        return repository.findAllPosts();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {


        EntityModel<User> model = EntityModel.of(getUser(id));
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkBuilder.withSelfRel());
        return model;
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable Integer id) {
        User user = getUser(id);
        return user.getPosts();
    }

    private User getUser(Integer id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        return user.get();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        User user = getUser(id);

        repository.deleteById(user.getId());
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
