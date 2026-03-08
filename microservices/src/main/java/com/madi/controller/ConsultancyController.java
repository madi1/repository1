package com.madi.controller;

// Import the DTO we created in the com.madi.dto package
import com.madi.dto.ConsultancyResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Madi Consultancy Services.
 * This class acts as the API bridge for the Angular frontend.
 */
@RestController
@RequestMapping("/api")
/* 
 * CORS: Allows your Angular app (port 4200) to safely 
 * request data from your Java backend (port 8080).
 */
@CrossOrigin(origins = "http://localhost:4200")
public class ConsultancyController {

    /**
     * Endpoint: GET http://localhost:8080/api/consultancy-message
     * Returns: A JSON object containing the consultancy message and status.
     */
    @GetMapping("/consultancy-message")
    public ResponseEntity<ConsultancyResponse> getConsultancyMessage() {
        
        // 1. Prepare the data (In a real app, this might come from a DB)
        ConsultancyResponse response = new ConsultancyResponse(
            "Madi Information Technology Consultancy: Enterprise Architecture & Cloud Transformation", 
            "SUCCESS"
        );

        // 2. Return the data wrapped in a 200 OK HTTP status
        // Spring automatically converts the 'response' object into JSON
        return ResponseEntity.ok(response);
    }
}
