package com.example.airlines.controller;


import com.example.airlines.dao.FlightDAO;
import com.example.airlines.model.AccountUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    private FlightDAO flightsDAO;

    @Autowired
    public MainController(FlightDAO flightsDAO) {
        this.flightsDAO = flightsDAO;
    }

    private String profile;

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal AccountUser user) {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", user);
        data.put("cities", flightsDAO.findAll());

        model.addAttribute("frontendData", data);

        return "index";
    }

}
