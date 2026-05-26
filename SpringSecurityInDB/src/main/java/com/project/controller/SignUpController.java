package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Person;
import com.project.repository.PersonRepository;

@RestController
public class SignUpController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PersonRepository personRepository;

	@PostMapping("/signup")
	public Person signup(@RequestBody Person person) {
		String encodedPassword = passwordEncoder.encode(person.getPassword());
		person.setPassword(encodedPassword);
		return personRepository.save(person); 
	}
}