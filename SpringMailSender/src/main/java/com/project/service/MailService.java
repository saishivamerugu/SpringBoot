package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	JavaMailSender javaMailSender;

	public String sendMail(String toEmail) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("letsknow77@gmail.com");
		mail.setTo(toEmail);
		mail.setSubject("FLM");
		mail.setText("Congrats on completing the JFS course !!...");
		javaMailSender.send(mail);
		return "Mail Sent Sucessuflly!...";
	}
}
