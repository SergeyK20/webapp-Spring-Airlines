package com.example.airlines.controller;


import com.example.airlines.dao.AccountUserDAO;
import com.example.airlines.model.AccountUser;
import com.example.airlines.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private AccountUserDAO accountUserDAO;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(AccountUser user, Map<String, Object> model) {
        AccountUser userFromDb = accountUserDAO.findByLogin(user.getUsername());


        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        accountUserDAO.save(user);

        return "redirect:/login";
    }
}
