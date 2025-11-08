package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.model.User;

@Controller
public class WelcomeController {
	
	@GetMapping("/sayHi")
	public String sayHi(Model model) { 
		model.addAttribute("name", "Sai");
		return "hi";
	}
	
	@PostMapping("/submit")
	public String submitForm(
	        @RequestParam String name,
	        @RequestParam String password,
	        Model model) {
	    System.out.println("Name: " + name);
	    System.out.println("Password: " + password);
	    model.addAttribute("name", name);
	    model.addAttribute("password", password);
	    return "sucess";
	}
	
	@GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User()); 
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") User user,
    								Model model) {
        model.addAttribute("user", user);
        return "register_success";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> list = new ArrayList<>();
        list.add(new User("Sai", "sai@gmail.com", "1234"));
        list.add(new User("Ram", "ram@gmail.com", "5678"));
        list.add(new User("John", "john@gmail.com", "abcd"));
        model.addAttribute("users", list);
        return "users";   // → users.html
    }
}
