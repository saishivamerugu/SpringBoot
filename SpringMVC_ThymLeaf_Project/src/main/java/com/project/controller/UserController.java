package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {

	@GetMapping("/home")
	@ResponseBody
	public String sayHi() { 
		return "home";
	}
}
