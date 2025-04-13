package com.rest.webservices.restful_web_services.social_media.dao;

import com.rest.webservices.restful_web_services.social_media.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
