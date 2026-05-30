package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Entity.Authority;
import com.project.Entity.Person;
import com.project.dao.PersonRepository;
import com.project.security.JWTService;

@RestController
@RequestMapping("/auth")
public class SignupController {

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTService jwtService;
	
	
//	@PostMapping("/signup")
//	public Person signup(@RequestBody Person person) {
//		String pass = passwordEncoder.encode(person.getPassword());
//		person.setPassword(pass);
//		return personRepository.save(person); 
//	} 
	
	@PostMapping("/signup")
	public Person signup(@RequestBody Person person) {
		String pass = passwordEncoder.encode(person.getPassword());
		person.setPassword(pass
		Authority authority = new Authority();
		authority.setRole("ROLE_USER");
		person.setAuthorities(List.of(authority));
		return personRepository.save(person);
	}
	
	@PostMapping("/loginUser")
	public ResponseEntity<String> login(
			@RequestBody Person person) {

		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(person.getEmail(), person.getPassword()));

		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body("Invalid Credentials");
		}

		String jwtToken =jwtService.generateToken(person.getEmail());

		return ResponseEntity.ok(jwtToken);
	}
	
}
