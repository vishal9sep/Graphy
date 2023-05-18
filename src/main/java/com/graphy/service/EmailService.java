package com.graphy.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.graphy.entity.User;
import com.graphy.repository.UserRepository;
import com.opencsv.CSVWriter;


@Service
public class EmailService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;
    
    private static final int PAGE_SIZE = 50;
    
    private static final String CSV_FILE_PATH = "C:\\Users\\Vishal\\OneDrive\\Desktop\\Graphy\\Emails_Log.csv";

    @Scheduled(fixedRate = 6 * 60 * 60 * 1000) // Runs every 6 hours :
    public void sendEmails() {
    	
    	int page = 0;
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, PAGE_SIZE));

        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH, true))) {
            while (!userPage.isEmpty()) {
            	
            	List<String[]> data = new ArrayList<>();                
                
                for (User user : userPage) {
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

                    data.add(new String[]{user.getName(), user.getEmail()});
                }
                writer.writeAll(data);

                userRepository.flush(); // Flush changes to the database

                page++;
                userPage = userRepository.findAll(PageRequest.of(page, PAGE_SIZE));
            }
        } catch (IOException e) {
        	System.out.println(e.getMessage());
            e.printStackTrace();
        }
        catch (Exception e) {                
        	System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
