package com.assignment.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String showLoginPage()
    {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDeniedForGet()
    {
        return "access-denied";
    }

    @PostMapping("/access-denied")
    public String accessDeniedForPost()
    {
        return "access-denied";
    }
}
