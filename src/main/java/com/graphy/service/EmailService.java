package com.graphy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.graphy.entity.User;
import com.graphy.repository.UserRepository;


@Service
public class EmailService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public List<User> sendEmails() {
    	
        List<User> users = userRepository.findAll(); //To Fetches 100 users at a time PageRequest.of(0, 100)

        List<User> sentEmails = new ArrayList<>();

        for (User user : users) {
            try {
                // Compose the email content
                String subject = "Demo main for testing ";
                String message = "Dear " + user.getName() + ",\n\n\"This is a demo Email for testing purposes.";

                // Create a SimpleMailMessage instance
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(user.getEmail());
                mailMessage.setSubject(subject);
                mailMessage.setText(message);

                // Send the email
                javaMailSender.send(mailMessage);

                // Add the user to the list of users to whom we have successfully sent emails
                sentEmails.add(user);                
            } 
            catch (Exception e) {                
            	System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

        return sentEmails;
    }
}
