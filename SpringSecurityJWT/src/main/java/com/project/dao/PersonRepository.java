package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	
	public Person findByEmail(String email);
}
