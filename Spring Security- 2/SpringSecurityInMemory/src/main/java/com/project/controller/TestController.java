package com.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/hi")
	public String hi() {
		return "HI !!!...";
	}
	
	@GetMapping("/bye")
	public String bye() {
		return "BYE !!!...";
	}
	
	@GetMapping("/open")
	public String open() {
		return "Open for all";
	}
	
	@GetMapping("/noAccess")
	public String noAccess() {
		return "No access";
		
	}
}
