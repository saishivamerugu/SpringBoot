package com.project.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.Entity.Authority;
import com.project.Entity.Person;
import com.project.dao.PersonRepository;

public class SecurityUserDetailsService implements UserDetailsService{

	@Autowired
	PersonRepository personRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person person = personRepository.findByEmail(username);
		
		// User take u name and pwd along with that he will take the list of authorities also 
		// we has to also send that list of authoruty 
		// We have a GrantedAuthority class where we have to send it 
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		// data base roles information should be given here 
		List<Authority> authoritiesFromDB = person.getAuthorities();
		for(Authority authority: authoritiesFromDB) {
			authorities.add(new SimpleGrantedAuthority(authority.getRole()));
		}
		  
		return User
				.builder()
				.username(person.getEmail())
				.password(person.getPassword())
				.authorities(authorities)
				.build();
	}

}
