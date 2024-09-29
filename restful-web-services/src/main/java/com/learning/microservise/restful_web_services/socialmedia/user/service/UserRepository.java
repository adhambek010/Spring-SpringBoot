package com.learning.microservise.restful_web_services.socialmedia.user.service;

import com.learning.microservise.restful_web_services.socialmedia.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
