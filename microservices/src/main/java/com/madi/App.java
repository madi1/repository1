package com.madi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // This tells Spring to register this app with Eureka
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
