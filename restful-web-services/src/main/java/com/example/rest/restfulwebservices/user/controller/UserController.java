package com.example.rest.restfulwebservices.user.controller;

import com.example.rest.restfulwebservices.user.User;
import com.example.rest.restfulwebservices.user.UserDaoService;
import com.example.rest.restfulwebservices.user.post.Post;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserDaoService userDaoService;

    @GetMapping
    public List<User> retrieveAllUsers(){
        return userDaoService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) throws ValidationException {
        var savedUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable int id){
        return userDaoService.findById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        userDaoService.deleteById(id);
    }
    @DeleteMapping
    public void deleteAllUsers() {
        userDaoService.deleteAll();
    }
    @PostMapping("/add-all")
    public ResponseEntity<?> saveAll (@RequestBody List<@Valid User> users) throws ValidationException {
        userDaoService.saveAll(users);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/posts/{id}")
    public List<Post> retrievePosts(@PathVariable int id){
        var user = userDaoService.findById(id);
        return user.getPosts();
    }
    @PostMapping("/posts/{id}")
    public ResponseEntity<?> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) throws ValidationException {
        var savedPost = userDaoService.createPostForUser(id, post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/posts/{id}").buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/posts")
    public List<Post> retrieveAllPosts(){
        return userDaoService.retrieveAllPosts();
    }
    @DeleteMapping("/posts/{id}/all-posts")
    public void deleteAllPosts(@PathVariable int id){
        userDaoService.deleteAllPosts(id);
    }
    @DeleteMapping("/posts/{id}/{id2}")
    public void deletePostById(@PathVariable int id, @PathVariable int id2){
        userDaoService.deletePostById(id, id2);
    }

}
