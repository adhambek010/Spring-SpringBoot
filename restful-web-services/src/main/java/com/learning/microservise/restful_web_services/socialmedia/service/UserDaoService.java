package com.learning.microservise.restful_web_services.socialmedia.service;

import com.learning.microservise.restful_web_services.socialmedia.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCount;

    static {
        userCount = 0;
        users.add(new User(++userCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount, "Tom", LocalDate.now().minusYears(25)));
        users.add(new User(++userCount, "Jim", LocalDate.now().minusYears(28)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(Integer id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void save(User user) {
        user.setId(++userCount);
        users.add(user);
    }

    public ResponseEntity<Object> deleteById(Integer id) {
        users.removeIf(user -> user.getId().equals(id));
        return ResponseEntity.noContent().build();
    }
}
