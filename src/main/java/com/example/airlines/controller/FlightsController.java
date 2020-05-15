package com.example.airlines.controller;

import com.example.airlines.dao.FlightsDAO;
import com.example.airlines.model.Flights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightsController {
    private FlightsDAO flightsDAO;

    @Autowired
    public FlightsController (FlightsDAO flightsDAO){
        this.flightsDAO=flightsDAO;
    }

    @GetMapping
    public List<Flights> getAllCity(){
        return flightsDAO.findAll();
    }

    @GetMapping("/{Id}")
    public Optional<Flights> getById(@PathVariable("Id") int id){
        return flightsDAO.findById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Flights saveFlight(@RequestBody Flights flight){
        return flightsDAO.save(flight);
    }

    /*@PutMapping("/{Id}")
    public void updateFlight(@RequestBody Flights flight, @PathVariable("Id")int id) {
        flightsDAO.update(flight.getNumFlight(),
                flight.getAirportsDeparture().getNameAirport(),
                flight.getAirportsDeparture().getAirportInTheCity().getNameCity(),
                flight.getAirportsArrival().getNameAirport(),
                flight.getAirportsArrival().getAirportInTheCity().getNameCity(),
                flight.getDepartureDate(),
                flight.getDepartureTime(),
                flight.getAircraftName(),
                id);
    }*/

    @PutMapping("/{Id}")
    public void updateFlight(@PathVariable("Id") int id, @RequestBody Flights flight){
        flightsDAO.findById(id).map(flights -> {
            flights.setNumFlight(flight.getNumFlight());
            flights.setAirportsDeparture(flight.getAirportsDeparture());
            flights.setAirportsArrival(flight.getAirportsArrival());
            flights.setDepartureDate(flight.getDepartureDate());
            flights.setDepartureTime(flight.getDepartureTime());
            flights.setAircraftName(flight.getAircraftName());
            return flightsDAO.save(flights);
        });
    }

    @DeleteMapping("/{Id}")
    public void deleteFlight(@PathVariable("Id") int id){
        flightsDAO.deleteById(id);
    }
}