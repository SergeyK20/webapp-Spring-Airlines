package com.example.airlines.controller;


import com.example.airlines.dao.AirportDAO;
import com.example.airlines.dao.CityDAO;
import com.example.airlines.dto.AirportDTO;
import com.example.airlines.exceptions.ExceptionWhenWorkingWithDB;
import com.example.airlines.exceptions.IdSearchException;
import com.example.airlines.model.Airport;
import com.example.airlines.model.City;
import com.example.airlines.parser.AirportParser;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonObject;
import java.util.List;
@RestController
@RequestMapping(value = "/airports")

public class AirportController {

    private AirportDAO airportDao;
    private CityDAO cityDAO;

    @Autowired
    public AirportController(AirportDAO airportDao, CityDAO cityDAO) {
        this.cityDAO = cityDAO;
        this.airportDao = airportDao;
    }

    @GetMapping
    /*@PreAuthorize("hasRole('ADMIN')")*/
    public List<AirportDTO> getAllAirports() {
        AirportDTO airportDTO = new AirportDTO();
        return airportDTO.airportListInAirportDTOList(airportDao.findAll());
    }

    @GetMapping("/{Id}")
    public AirportDTO getById(@PathVariable("Id") int id) {
        AirportDTO airportDTO = new AirportDTO();
        if(airportDao.findById(id).isPresent()) {
            return airportDTO.airportInAirportDTO(airportDao.findById(id).get());
        } else {
            throw new IdSearchException("did not found airport");
        }
    }

    @GetMapping("/findByNameCity/{name}")
    public List<AirportDTO> getByNameCity(@PathVariable("name") String name) {
        AirportDTO airportDTO = new AirportDTO();
        return airportDTO.airportListInAirportDTOList(airportDao.getByNameCity(name));
    }

    @PutMapping("/{Id}")
    public void updateAirport(@PathVariable("Id") int id, @RequestBody String airportString) throws JSONException {
        Airport airport = AirportParser.airportParser(airportString);
        airportDao.findById(id).map(airports -> {
            airports.setNameAirport(airport.getNameAirport());
            if(cityDAO.findById(airport.getAirportInTheCity().getId()).isPresent()) {
                airports.setAirportInTheCity(cityDAO.findById(airport.getAirportInTheCity().getId()).get());
            } else {
                throw new ExceptionWhenWorkingWithDB("Did not update airport");
            }
            return airportDao.save(airports);
        });
    }

    /**
     * @param
     *                {
     *                "nameAirport": "name_airport",
     *                "airportInTheCity": {
     *                "id": 1
     *                }
     *                }
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public AirportDTO saveAirport(@RequestBody String  airportString) throws ParseException, JSONException {
        AirportDTO airportDTO = new AirportDTO();
        Airport airport = AirportParser.airportParser(airportString);
        if(cityDAO.findById(airport.getAirportInTheCity().getId()).isPresent()) {
            try {
                airport.setAirportInTheCity(cityDAO.findById(airport.getAirportInTheCity().getId()).get());
                return airportDTO.airportInAirportDTO(airportDao.save(airport));
            } catch(Exception e){
                throw new ExceptionWhenWorkingWithDB("Did not create airport");
            }
        } else {
        throw new IdSearchException("did not found city");
        }
    }



    @DeleteMapping("/{Id}")
    public void deleteAirport(@PathVariable("Id") int id) {
        try {
            airportDao.deleteById(id);
        } catch (Exception e) {
            throw new ExceptionWhenWorkingWithDB("Did not delete airport");
        }
    }
}
