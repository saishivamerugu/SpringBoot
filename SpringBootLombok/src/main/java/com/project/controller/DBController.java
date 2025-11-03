package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.SpringBootLombokApplication;
import com.project.model.Employee;

@RestController
public class DBController {

    private final SpringBootLombokApplication springBootLombokApplication;

    @Autowired
    JdbcTemplate jdbcTemplate;

    DBController(SpringBootLombokApplication springBootLombokApplication) {
        this.springBootLombokApplication = springBootLombokApplication;
    }

    @GetMapping("/save")
    public String save() {
        jdbcTemplate.update(
                "INSERT INTO employee (email, password) VALUES (?, ?)",
                "sai2@gmail.com", "sai123"
        );

        return "<h2>Employee saved successfully!</h2>";
    }

    @GetMapping("/getData")
    public void getEmployee() {
        Employee emp =   jdbcTemplate.queryForObject(
                "SELECT email, password FROM employee WHERE email = ?",
                BeanPropertyRowMapper.newInstance(Employee.class),
                "sai2@gmail.com"
        );
        System.out.println(emp);
    }


}
