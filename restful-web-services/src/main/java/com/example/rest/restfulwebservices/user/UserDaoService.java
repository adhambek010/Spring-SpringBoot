package com.example.rest.restfulwebservices.user;

import com.example.rest.restfulwebservices.exception.UserNotFoundException;
import com.example.rest.restfulwebservices.user.post.Post;
import com.example.rest.restfulwebservices.user.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDaoService {
    private final UserRepository repository;
    private final PostRepository postRepository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User findById(Integer id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }else {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
    }

    public void deleteById(Integer id) {
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            repository.deleteById(userOptional.get().getId());
        } else {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void saveAll(List<User> users) {
        repository.saveAll(users);
    }

    public Post createPostForUser(int id, Post post) {
        var user = findById(id);
        post.setUser(user);
        postRepository.save(post);
        return user.setPost(post);
    }

    public List<Post> retrieveAllPosts() {
        return postRepository.findAll();
    }

    public void deleteAllPosts(int id) {
        var user = findById(id);
        var posts = user.getPosts();
        for (var post : posts) {
            var postId = post.getId();
            postRepository.deleteById(postId);
        }
    }

    public void deletePostById(int id, int id2) {
        var user = findById(id);
        var posts = user.getPosts();

        posts.stream()
                .filter(post -> post.getId() == id2)
                .findFirst()
                .ifPresent(post -> postRepository.deleteById(post.getId()));
    }

}
