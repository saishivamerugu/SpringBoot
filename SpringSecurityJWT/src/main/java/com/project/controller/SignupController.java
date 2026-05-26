package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Entity.Person;
import com.project.dao.PersonRepository;

@RestController
public class SignupController {

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping("/signup")
	public Person signup(@RequestBody Person person) {
		String pass = passwordEncoder.encode(person.getPassword());
		person.setPassword(pass);
		return personRepository.save(person);
	}
}
