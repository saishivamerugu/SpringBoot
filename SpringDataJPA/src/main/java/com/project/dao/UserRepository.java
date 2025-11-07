package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	// TO write the hql for the below we can annotate the query directly 
	//In JPQL we dont use the sql we only use the entity name and the  
	
	@Query("SELECT u FROM User u WHERE u.name = :name")
	public User findByName(@Param("name") String name);
 // abstract method 
//	we has to say the name in the query and the method are same ...
//	if there are multiple users use List<User>
	// if we give @Column we has to give the @param and ir gives the order and we can also take the question mark 
	// 
}

/*
 * i need a template or session factory  to connect to the data base 
 * we need an interface to connect 
 * there is some thing called JPARepository
 * when we are working with spring data jpa it will offer the JPARepository
 * It is expecting two arguments
 * it is expecting a model class 
 * to which model class you want to link 
 * the primary key type 
 * we need the primary key to work 
 * @Repository - have the component internally 
 * For JPA we write a interface empty one and extend with the jpa repsoitory 
 * parent interface is repository .. it is the at most interface 
 * 
 * Parent - at most - Repository 
 * and then it is Paging and Sorting Repository 
 * JPARepository 
 * CRUD repository 
 * jpa is more advanced than the crud repository 
 * crud repo is also a repo refereing the repository 
 * 
 * but the JPARepo have added features than the crud repostiory due to some added features 
 * to find the person through name 
 * so we has to write the seperate query for it 
 * generally we have four methods 
 * persist find merge remove 
 * 
 * to find seperately we used to write HQL 
 * now in the JPA we have JPQL - jakarta persistance query language 
 * there is a bit difference in both of them 
 * 
 *  User findByName();
 * 
 */
