package com.example.airlines.controller;

import com.example.airlines.dao.CityDAO;
import com.example.airlines.dto.CityDTO;
import com.example.airlines.exceptions.ExceptionWhenWorkingWithDB;
import com.example.airlines.exceptions.IdSearchException;
import com.example.airlines.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/city")
/*@PreAuthorize("hasRole('ADMIN')")*/
public class CityController {

    private CityDAO cityDAO;

    @Autowired
    public CityController(CityDAO cityDao) {
        this.cityDAO = cityDao;
    }

    @GetMapping
    /*@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")*/
    public List<CityDTO> getAllCity() {
        CityDTO cityDTO = new CityDTO();
        return cityDTO.cityListInCityDTOList(cityDAO.findAll());
    }

    @GetMapping("/{Id}")
    public CityDTO getById(@PathVariable("Id") int id) {
        CityDTO cityDTO = new CityDTO();
        if(cityDAO.findById(id).isPresent()) {
            return cityDTO.cityInCityDTO(cityDAO.findById(id).get());
        } else {
            throw new IdSearchException("did not found city");
        }
    }

    /**
     * @param city в теле должны обязательно находится "nameCity"
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public City saveCity(@RequestBody City city) {
        try {
            return cityDAO.save(city);
        } catch(Exception e){
            throw new ExceptionWhenWorkingWithDB("Did not create city");
        }
    }

    /**
     * @param city в теле должны обязательно находится "nameCity"
     */
    @PutMapping("/{Id}")
    public void updateCity(@PathVariable("Id") int id, @RequestBody City city) {
        try {
            cityDAO.findById(id).map(city1 -> {
                city1.setNameCity(city.getNameCity());
                return cityDAO.save(city1);
            });
        } catch (Exception e){
            throw new ExceptionWhenWorkingWithDB("Did not update city");
        }
    }

    @DeleteMapping("/{Id}")
    public void deleteCity(@PathVariable("Id") int id) {
        try {
            cityDAO.deleteById(id);
        } catch (Exception e){
            throw new ExceptionWhenWorkingWithDB("Did not delete city");
        }
    }
}
