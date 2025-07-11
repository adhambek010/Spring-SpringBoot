package com.rest.webservices.restful_web_services.user.controller;

import com.rest.webservices.restful_web_services.user.database.entities.Post;
import com.rest.webservices.restful_web_services.user.database.entities.User;
import com.rest.webservices.restful_web_services.user.database.repositoriess.PostRepository;
import com.rest.webservices.restful_web_services.user.database.repositoriess.UserRepository;
import com.rest.webservices.restful_web_services.user.exception.PostNotFoundException;
import com.rest.webservices.restful_web_services.user.exception.UserNotFoundException;
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
public class UserResource {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {


        EntityModel<User> model = EntityModel.of(getUser(id));
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkBuilder.withSelfRel());
        return model;
    }

    private User getUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        return user.get();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        User user = getUser(id);

        userRepository.deleteById(user.getId());
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //Post
    @GetMapping("/users/posts")
    public List<Post> retrieveAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable Integer id) {
        User user = getUser(id);
        return user.getPosts();
    }

    @GetMapping("/users/{uId}/posts/{id}")
    public EntityModel<Post> retrievePost(@PathVariable("id") Integer postId,
                                          @PathVariable("uId") Integer userId) {
        EntityModel<Post> model = EntityModel.of(getPost(postId));

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(this.getClass())
                        .retrievePost(postId, userId));

        model.add(linkBuilder.withSelfRel());
        return model;
    }

    private Post getPost(Integer id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            throw new PostNotFoundException("Post with id " + id + " not found");
        }
        return post.get();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable Integer id,
                                             @Valid @RequestBody Post post) {
        User savedUser = getUser(id);
        post.setUser(savedUser);
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users/{userId}/posts/{postId}")
                .buildAndExpand(id, savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}