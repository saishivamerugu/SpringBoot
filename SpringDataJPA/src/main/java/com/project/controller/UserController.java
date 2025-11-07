package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/save")
    public String saveUser() {
        String name = userService.saveUser();
        return name + " saved successfully!";
    }

    @GetMapping("/getUser")
    public void findUser() {
         userService.getUser();
    }

    @GetMapping("/getAllUsers")
    public void getUsers() {
        userService.getAllUsers();
    }
}

