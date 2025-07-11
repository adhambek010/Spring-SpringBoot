package com.rest.webservices.restful_web_services.user.database.repositoriess;

import com.rest.webservices.restful_web_services.user.database.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

}
