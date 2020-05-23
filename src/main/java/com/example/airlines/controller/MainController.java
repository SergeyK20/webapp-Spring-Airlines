package com.example.airlines.controller;

import com.example.airlines.dao.FlightsDAO;
import com.example.airlines.model.Flights;
import com.example.airlines.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    private FlightsDAO flightsDAO;

    @Autowired
    public MainController (FlightsDAO flightsDAO){
        this.flightsDAO=flightsDAO;
    }

    private String profile;

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();
        data.put("flights", user.getFlights());

        model.addAttribute("frontendData", data);

        return "index";
    }

}
