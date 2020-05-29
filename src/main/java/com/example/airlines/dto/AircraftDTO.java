package com.example.airlines.dto;

import com.example.airlines.model.Aircraft;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс  выводящий самолеты
 */
public class AircraftDTO {
    private int id;
    private String nameAircraft;
    private int numberSeatsAircraft;

    public AircraftDTO() {
    }

    public AircraftDTO(int id, String nameAircraft, int numberSeatsAircraft) {
        this.id = id;
        this.nameAircraft = nameAircraft;
        this.numberSeatsAircraft = numberSeatsAircraft;
    }

    public List<AircraftDTO> aircraftListInAircraftDTOList(List<Aircraft> aircraftList){
        if(aircraftList == null){
            return null;
        }

        List<AircraftDTO> aircraftDTOList = new ArrayList<>();

        for(Aircraft aircraft : aircraftList){
            aircraftDTOList.add(aircraftInAircraftDTO(aircraft));
        }
        return aircraftDTOList;
    }

    public AircraftDTO aircraftInAircraftDTO(Aircraft aircraft){
        if(aircraft == null){
            return null;
        }

        AircraftDTO aircraftDTO = new AircraftDTO();
        aircraftDTO.setId(aircraft.getId());
        aircraftDTO.setNameAircraft(aircraft.getNameAircraft());
        aircraftDTO.setNumberSeatsAircraft(aircraft.getNumberSeatsAircraft());
        return aircraftDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAircraft() {
        return nameAircraft;
    }

    public void setNameAircraft(String nameAircraft) {
        this.nameAircraft = nameAircraft;
    }

    public int getNumberSeatsAircraft() {
        return numberSeatsAircraft;
    }

    public void setNumberSeatsAircraft(int numberSeatsAircraft) {
        this.numberSeatsAircraft = numberSeatsAircraft;
    }
}
