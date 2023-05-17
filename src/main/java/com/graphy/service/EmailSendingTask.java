package com.graphy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.graphy.entity.User;

@Component
public class EmailSendingTask {
    @Autowired
    private EmailService emailService;

    @Autowired
    private CSVLoggingService csvLoggingService;

    @Scheduled(fixedRate = 6 * 60 * 60 * 1000) // Runs every 6 hours
    public void sendEmailsAndLog() {
        List<User> successfullySentEmails = emailService.sendEmails();
        csvLoggingService.logSuccessfulEmails(successfullySentEmails);
    }
}
