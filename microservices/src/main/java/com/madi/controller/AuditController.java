package com.madi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allows your Angular app to talk to this API
public class AuditController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/book-audit")
    public void sendAuditRequest(@RequestBody Map<String, String> request) {
        String userEmail = request.get("email");
        String userLetter = request.get("letter");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("madi.java.consultancy@gmail.com"); // Your inbox
        message.setSubject("New Strategic Audit Request");
        message.setText("From: " + userEmail + "\n\nMessage:\n" + userLetter);

        if (mailSender instanceof JavaMailSenderImpl) {
            JavaMailSenderImpl impl = (JavaMailSenderImpl) mailSender;
            System.out.println("madi Host: " + impl.getHost());
            System.out.println("madi Port: " + impl.getPort());
            System.out.println("madi User: " + impl.getUsername());
            System.out.println("madi Pass: " + impl.getPassword());
        }
        mailSender.send(message);
    }
}
