package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.dao.UserRepository;
import com.project.model.User;

@Service
public class UserService {
	
    @Autowired
    UserRepository userRepository;

    public String saveUser() {
        User user = new User("Fayaz", 21);
        User savedUser = userRepository.save(user);

        return savedUser.getName();  
    }
    
    public void getUser() {
    	Optional<User> retrivedUser = userRepository.findById(1);
    	User user = retrivedUser.get();
    	System.out.println(user);
    }
    
    public void getAllUsers() {
    	List<User> allUsers = userRepository.findAll();
    	System.out.println(allUsers);
    }
    
    
}


// we write all the logics inside the UserService class
// login operation ... username and password .. this has to be checked .. by calling the data base and checking if they exists or not ... 
// COntroller is there to send and recieve the response 
// But the business logic is written inside the service
/* @Service will also have the @Componenet they have provided extra annotations for readability and to know to to the persons it is a service class 
 * controller -> API endpoints 
 * Service -> Business Logic
 * DAO -> data base relate configs
 * we are getting the save method from the JPA
 */
