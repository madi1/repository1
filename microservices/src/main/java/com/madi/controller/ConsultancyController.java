package com.madi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/consultancy")
@CrossOrigin(origins = "*")
public class ConsultancyController {

    @GetMapping
    public Map<String, String> getConsultancyDataonsultancyData() {
        Map<String, String> data = new HashMap<>();
        // Example string payload the front end can consume
        data.put("payload", "my name is AI");
        data.put("message", "Welcome to Madi Consultancy!");
        data.put("status", "Backend Connected");
        return data;
    }
}
