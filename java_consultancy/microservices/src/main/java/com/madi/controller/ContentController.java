package com.madi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ContentController {

    @GetMapping("/api/content/welcome")
    public String welcome() {
        return "Madi Information Technology Consultancy";
    }
}