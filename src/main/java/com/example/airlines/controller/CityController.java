package com.example.airlines.controller;

import com.example.airlines.dao.CityDAO;
import com.example.airlines.dto.CityDTO;
import com.example.airlines.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/city")
public class CityController {

    private CityDAO cityDAO;

    @Autowired
    public CityController(CityDAO cityDao) {
        this.cityDAO = cityDao;
    }

    @GetMapping
    public List<CityDTO> getAllCity() {
        CityDTO cityDTO = new CityDTO();
        return cityDTO.cityListInCityDTOList(cityDAO.findAll());
    }

    @GetMapping("/{Id}")
    public CityDTO getById(@PathVariable("Id") int id) {
        CityDTO cityDTO = new CityDTO();
        return cityDTO.cityInCityDTO(cityDAO.findById(id).get());
    }

    /**
     * @param city в теле должны обязательно находится "nameCity"
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public City saveCity(@RequestBody City city) {
        return cityDAO.save(city);
    }

    /**
     * @param city в теле должны обязательно находится "nameCity"
     */
    @PutMapping("/{Id}")
    public void updateCity(@PathVariable("Id") int id, @RequestBody City city) {
        cityDAO.findById(id).map(city1 -> {
            city1.setNameCity(city.getNameCity());
            return cityDAO.save(city1);
        });
    }

    @DeleteMapping("/{Id}")
    public void deleteCity(@PathVariable("Id") int id) {
        cityDAO.deleteById(id);
    }
}
