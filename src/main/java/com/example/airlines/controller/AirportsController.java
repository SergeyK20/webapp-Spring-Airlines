package com.example.airlines.controller;

import com.example.airlines.dao.AirportsDAO;
import com.example.airlines.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/airports")
@PreAuthorize("hasAuthority('ADMIN')")
public class AirportsController {
    private AirportsDAO airportsDao;

    @Autowired
    public AirportsController (AirportsDAO airportsDao){
        this.airportsDao=airportsDao;
    }

    @GetMapping
    public List<Airport> getAllAirports(){
        return airportsDao.findAll();
    }

    @GetMapping("/{Id}")
    public java.util.Optional<Airport> getById(@PathVariable("Id") int id){
        return airportsDao.findById(id);
    }

    @GetMapping("/findByNameCity/{name}")
    public List<Airport> getByNameCity(@PathVariable("name") String name) {
        return airportsDao.getByNameCity(name);
    }

    @PutMapping("/{Id}")
    public void updateAirport(@PathVariable("Id") int id, @RequestBody Airport airport){
        airportsDao.findById(id).map(airports -> {
                                        airports.setNameAirport(airport.getNameAirport());
                                        airports.setAirportInTheCity(airport.getAirportInTheCity());
                                    return  airportsDao.save(airports);
        });
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Airport saveAirport(@RequestBody Airport airport){
        return airportsDao.save(airport);
    }

    @DeleteMapping("/{Id}")
    public void deleteAirport(@PathVariable("Id") int id){
        airportsDao.deleteById(id);
    }
}
