package com.learning.microservise.restful_web_services.socialmedia.post.service;

import com.learning.microservise.restful_web_services.exception.PostNotFoundException;
import com.learning.microservise.restful_web_services.exception.UserNotFoundException;
import com.learning.microservise.restful_web_services.socialmedia.post.Post;
import com.learning.microservise.restful_web_services.socialmedia.user.User;
import com.learning.microservise.restful_web_services.socialmedia.user.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PostResource {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @GetMapping("/users/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User is not found with id " + id);
        }
        return postRepository.findByUserId(id);
    }

    @PostMapping("users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable Integer id ,@RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User is not found with id " + id);
        }
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getPost_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("users/{user_id}/posts/{post_id}")
    public void deletePost(@PathVariable Integer post_id, @PathVariable Integer user_id) {
        Optional<User> user = userRepository.findById(user_id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User is not found with id " + post_id);
        }
        Optional<Post> post = postRepository.findById(post_id);
        if(post.isEmpty()){
            throw new PostNotFoundException("Post is not found with id " + post_id);
        }
        postRepository.deleteById(post_id);
    }

}
