package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.service.MailService;

@RestController
public class MailController {

	@Autowired
	MailService mailService;
	
	@GetMapping("/send")
	public String sendEmail(@RequestParam(name="toEmail") String toEmail) {
		return mailService.sendMail(toEmail);
	}
}
