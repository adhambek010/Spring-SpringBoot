package com.rest.webservices.restful_web_services.social_media.contollers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DbTestController {


    private final JdbcTemplate jdbcTemplate;

    @GetMapping("/test-db")
    public List<Map<String, Object>> testDb() {
        return jdbcTemplate.queryForList("SELECT * FROM user_details LIMIT 1");
    }
}