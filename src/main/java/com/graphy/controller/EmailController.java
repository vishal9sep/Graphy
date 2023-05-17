package com.graphy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graphy.entity.User;
import com.graphy.service.CSVLoggingService;
import com.graphy.service.EmailService;

@RestController("/")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@Autowired
	private CSVLoggingService csvLoggingService;

	@GetMapping("/send")
    public String sendEmails() {

		List<User> sentEmails = emailService.sendEmails();
        csvLoggingService.logSuccessfulEmails(sentEmails);
        return "Emails sent successfully.";
    }

}
