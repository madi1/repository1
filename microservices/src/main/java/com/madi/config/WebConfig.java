package com.madi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull; // 1. Import the Spring NonNull annotation
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) { // 2. Add @NonNull here
        registry.addMapping("/**")
                .allowedOrigins(
                    "http://localhost:4200", 
                    "azurestaticapps.net", 
                    "https://maditech.online", 
                    "maditech.online"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Origin", "Content-Type", "Accept", "Authorization")
                .allowCredentials(true);
    }
}
