package com.learning.microservise.restful_web_services.socialmedia.post.service;

import com.learning.microservise.restful_web_services.socialmedia.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUserId(Integer userId);
}
