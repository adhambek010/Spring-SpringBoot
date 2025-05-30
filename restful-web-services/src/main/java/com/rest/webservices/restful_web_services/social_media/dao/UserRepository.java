package com.rest.webservices.restful_web_services.social_media.dao;

import com.rest.webservices.restful_web_services.social_media.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
