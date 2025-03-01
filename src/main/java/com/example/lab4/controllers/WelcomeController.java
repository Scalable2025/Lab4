package com.example.lab4.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @Value("${instance_name:default instance}")
    private String instance_name;

    @GetMapping
    public String welcome() {
        return "Welcome to: " + instance_name;
    }
}
