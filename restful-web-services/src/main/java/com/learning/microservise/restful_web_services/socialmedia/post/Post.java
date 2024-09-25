package com.learning.microservise.restful_web_services.socialmedia.post;

import com.learning.microservise.restful_web_services.socialmedia.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Integer post_id;
    private String title;
    private String content;
    @ManyToOne
    private User user;

}
