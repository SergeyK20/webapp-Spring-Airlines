
package com.example.airlines.dto;

import com.example.airlines.model.Flight;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс рейса передающийся клиенту, без аккаунтов
 */
public class FlightRoleUserDTO {
    private int idFlight;
    private String numFlight;
    private AirportDTO airportDeparture;
    private AirportDTO airportArrival;
    private Date departureDate;
    private Time departureTime;
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
        flightRoleUserDTO.setIdFlight(flight.getId());
        flightRoleUserDTO.setAircraft(new AircraftDTO().aircraftInAircraftDTO(flight.getAircraft()));
        flightRoleUserDTO.setAirportDeparture(new AirportDTO().airportInAirportDTO(flight.getAirportDeparture()));
        flightRoleUserDTO.setAirportArrival(new AirportDTO().airportInAirportDTO(flight.getAirportArrival()));
        flightRoleUserDTO.setNumFlight(flight.getNumFlight());
        flightRoleUserDTO.setDepartureDate(flight.getDepartureDate());
        flightRoleUserDTO.setDepartureTime(flight.getDepartureTime());
        flightRoleUserDTO.setPrice(flight.getPrice());
        return flightRoleUserDTO;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
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

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
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
