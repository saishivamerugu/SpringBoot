package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Props;

@RestController
public class TestContoller {
	
	@Autowired
	Props props; 

	@GetMapping("/props")
	public Props getProps() {
		return props;
	}
}

