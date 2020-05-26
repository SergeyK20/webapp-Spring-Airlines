package com.example.airlines.dto;

import com.example.airlines.model.Airport;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс выводящий аэропорты
 */
public class AirportDTO {

    private int id;
    private String nameAirport;
    private CityDTO airportInTheCity;

    public AirportDTO() {
    }

    public List<AirportDTO> airportListInAirportDTOList(List<Airport> airportList) {
        if (airportList == null) {
            return null;
        }

        List<AirportDTO> airportDTOList = new ArrayList<>();

        for (Airport airport : airportList) {
            airportDTOList.add(airportInAirportDTO(airport));
        }

        return airportDTOList;
    }

    public AirportDTO airportInAirportDTO(Airport airport) {
        if (airport == null) {
            return null;
        }
        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setId(airport.getId());
        airportDTO.setNameAirport(airport.getNameAirport());
        airportDTO.setAirportInTheCity(new CityDTO().cityInCityDTO(airport.getAirportInTheCity()));
        return airportDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAirport() {
        return nameAirport;
    }

    public void setNameAirport(String nameAirport) {
        this.nameAirport = nameAirport;
    }

    public CityDTO getAirportInTheCity() {
        return airportInTheCity;
    }

    public void setAirportInTheCity(CityDTO airportInTheCity) {
        this.airportInTheCity = airportInTheCity;
    }
}
