package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/save")
    public String save() {
        jdbcTemplate.update("INSERT INTO employee (email, password) VALUES (?, ?)",
                "sai2@gmail.com", "sai123");

        return "<h2>Employee saved successfully!</h2>";
    }
}
