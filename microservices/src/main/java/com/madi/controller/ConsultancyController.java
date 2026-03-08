package com.madi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Madi Consultancy Services.
 * Handled via @RestController to return raw data (Strings/JSON) instead of Views.
 */
@RestController
@RequestMapping("/api")
// Priority for Banking: Restricted access. 
// Replace "*" with "http://localhost:4200" for production-level security.
@CrossOrigin(origins = "http://localhost:4200") 
public class ConsultancyController {

    @GetMapping("/consultancy-message")
    public String getConsultancyMessage() {
        // This string will be fetched and displayed by your Angular component
        return "Madi Information Technology Consultancy: Enterprise Architecture & Cloud Transformation";
    }
}
