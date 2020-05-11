package com.example.airlines.controller;

import com.example.airlines.dao.AccessDAO;
import com.example.airlines.model.Access;
import com.example.airlines.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController(value = "/access")
public class AccessController {

    private AccessDAO accessDAO;

    @Autowired
    public AccessController(AccessDAO accessDAO) {
        this.accessDAO = accessDAO;
    }

    @GetMapping
    public List<Access> getAllAccess() {
        return accessDAO.findAll();
    }

    @GetMapping("/{Id}")
    public Optional<Access> getById(@PathVariable("Id") int id) {
        return accessDAO.findById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Access saveAccess(@RequestBody Access access) {
        return accessDAO.save(access);
    }

    @PutMapping("/{Id}")
    public void updateAccess(@PathVariable("Id") int id, @RequestBody Access access) {
        accessDAO.findById(id).map(access1 -> {
            access1.setNameAccess(access.getNameAccess());
            return accessDAO.save(access1);
        });
    }

    @DeleteMapping("/{Id}")
    public void deleteAccess(@PathVariable("Id") int id) {
        accessDAO.deleteById(id);
    }
}
