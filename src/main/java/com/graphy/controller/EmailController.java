package com.graphy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graphy.service.EmailService;

@RestController("/")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@GetMapping("/send")
    public  ResponseEntity<String> sendEmails() {

		try {
			emailService.sendEmails();
	        return ResponseEntity.ok("Emails sent successfully");			
		}
		 catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send emails");
        }		
    }

}
