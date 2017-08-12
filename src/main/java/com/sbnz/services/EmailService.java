package com.sbnz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Email service, sending email to user who wants to register
 * @author Predrag Falcic
 *
 */
@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	/**
	 * Constructor with parameter
	 * @param mailSender
	 */
	@Autowired
	public EmailService(JavaMailSender mailSender){
		this.mailSender = mailSender;
	}
	
	/**
	 * Function to send email message
	 * @param email
	 */
	@Async
	public void sendEmail(SimpleMailMessage email){
		mailSender.send(email);
	}
}
