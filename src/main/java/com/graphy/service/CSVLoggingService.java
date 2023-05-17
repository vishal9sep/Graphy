package com.graphy.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.graphy.entity.User;
import com.opencsv.CSVWriter;

@Service
public class CSVLoggingService {
    private static final String CSV_FILE_PATH = "C:\\Users\\Vishal\\OneDrive\\Desktop\\Graphy\\emails.csv";

//    public void logSuccessfulEmails(List<String> emailAddresses) {
//        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH, true))) {
//            for (String emailAddress : emailAddresses) {
//                writer.writeNext(new String[]{emailAddress});
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle the exception as per your requirement
//        }
//    }
    
    public void logSuccessfulEmails(List<User> users) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH, true))) {
            List<String[]> data = new ArrayList<>();
            for (User user : users) {
                data.add(new String[]{user.getName(), user.getEmail()});
            }
            writer.writeAll(data);
        } catch (IOException e) {
        	System.out.println(e.getMessage());
        	e.printStackTrace();
        }
    }
}