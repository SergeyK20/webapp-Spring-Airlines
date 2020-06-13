
package com.example.airlines.dto;

import com.example.airlines.model.Flight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс рейса передающийся клиенту, без аккаунтов
 */
public class FlightRoleUserDTO {
    private int id;
    private String numFlight;
    private AirportDTO airportDeparture;
    private AirportDTO airportArrival;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private AircraftDTO aircraft;
    private double price;


    public FlightRoleUserDTO() {
    }

    public List<FlightRoleUserDTO> flightListToFlightClientDTOList(List<Flight> flights) {
        if (flights == null) {
            return null;
        }

        List<FlightRoleUserDTO> flightRoleUserDTOS = new ArrayList<>();

        for (Flight flight : flights) {
            flightRoleUserDTOS.add(addValuesInFlightClientDTO(flight));
        }
        return flightRoleUserDTOS;
    }

    public FlightRoleUserDTO flightToFlightClientDTO(Flight flight) {
        if (flight == null) {
            return null;
        }
        return addValuesInFlightClientDTO(flight);
    }


    public FlightRoleUserDTO addValuesInFlightClientDTO(Flight flight) {
        FlightRoleUserDTO flightRoleUserDTO = new FlightRoleUserDTO();
        flightRoleUserDTO.setId(flight.getId());
        AircraftDTO aircraftDTO = new AircraftDTO(flight.getAircraft().getId(),
                                                  flight.getAircraft().getNameAircraft(),
                                flight.getAircraft().getNumberSeatsAircraft() - flight.getAccountUsers().size());
        flightRoleUserDTO.setAircraft(aircraftDTO);
        flightRoleUserDTO.setAirportDeparture(new AirportDTO().airportInAirportDTO(flight.getAirportDeparture()));
        flightRoleUserDTO.setAirportArrival(new AirportDTO().airportInAirportDTO(flight.getAirportArrival()));
        flightRoleUserDTO.setNumFlight(flight.getNumFlight());
        flightRoleUserDTO.setDepartureDate(flight.getDepartureDate());
        flightRoleUserDTO.setDepartureTime(flight.getDepartureTime());
        flightRoleUserDTO.setPrice(flight.getPrice());
        return flightRoleUserDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumFlight() {
        return numFlight;
    }

    public void setNumFlight(String numFlight) {
        this.numFlight = numFlight;
    }

    public AirportDTO getAirportDeparture() {
        return airportDeparture;
    }

    public void setAirportDeparture(AirportDTO airportDeparture) {
        this.airportDeparture = airportDeparture;
    }

    public AirportDTO getAirportArrival() {
        return airportArrival;
    }

    public void setAirportArrival(AirportDTO airportArrival) {
        this.airportArrival = airportArrival;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public AircraftDTO getAircraft() {
        return aircraft;
    }

    public void setAircraft(AircraftDTO aircraft) {
        this.aircraft = aircraft;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
