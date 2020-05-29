package com.example.airlines.dto;

import com.example.airlines.model.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс выводящий города
 */
public class CityDTO {

    private int id;
    private String nameCity;

    public CityDTO() {
    }

    public List<CityDTO> cityListInCityDTOList(List<City> cityList) {
        if (cityList == null) {
            return null;
        }

        List<CityDTO> cityDTOList = new ArrayList<>();

        for (City city : cityList) {
            cityDTOList.add(cityInCityDTO(city));
        }
        return cityDTOList;
    }

    public CityDTO cityInCityDTO(City city) {
        if (city == null) {
            return null;
        }
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setNameCity(city.getNameCity());
        return cityDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }
}
