package com.example.cp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.Collections;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class RestServerApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RestServerApplication.class);
        app.run(args);
    }
}
