package com.example.airlines.controller;

import com.example.airlines.dao.CityDAO;
import com.example.airlines.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/city")
@PreAuthorize("hasAuthority('ADMIN')")
public class CityController {

    private CityDAO cityDAO;

    @Autowired
    public CityController (CityDAO cityDao){
        this.cityDAO =cityDao;
    }

    @GetMapping
    public List<City> getAllCity(){
       return cityDAO.findAll();
    }

    @GetMapping("/{Id}")
    public Optional<City> getById(@PathVariable("Id") int id){
        return cityDAO.findById(id);
    }

    @PostMapping
    public City createCity(@RequestBody City city){
        return cityDAO.save(city);
    }

    @PutMapping("/city")
    public void updateCity(@PathVariable("Id") int id, @RequestBody City city){
        cityDAO.findById(id).map(city1 ->{
                                city1.setNameCity(city.getNameCity());
                                return cityDAO.save(city1);
        });
    }

    @DeleteMapping("/{Id}")
    public void deleteCity(@PathVariable("Id") City city){
        cityDAO.delete(city);
    }
}
