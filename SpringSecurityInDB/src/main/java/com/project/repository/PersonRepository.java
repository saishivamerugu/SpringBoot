package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

	Person findByUserName(String username);
}