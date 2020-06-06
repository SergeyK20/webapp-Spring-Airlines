package com.example.airlines.controller;

import com.example.airlines.model.AuthenticationBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
@RequestMapping("/login")
public class AuthController {

    @GetMapping(path ="/login")
    public AuthenticationBean basicauth() {
        return new AuthenticationBean(" You are authenticated");
    }

}
