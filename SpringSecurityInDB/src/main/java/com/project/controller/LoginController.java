package com.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Person;

@RestController
public class LoginController {
	
	@PostMapping("/user/login")
	public String login(@RequestBody Person person) {
		return "You are logged in..";
	}
}
