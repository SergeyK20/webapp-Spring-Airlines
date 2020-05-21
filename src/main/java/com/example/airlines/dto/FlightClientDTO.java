package com.example.airlines.dto;

import com.example.airlines.model.Airport;
import com.example.airlines.model.Flight;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс рейса передающийся клиенту
 */
public class FlightClientDTO {
    private int idFlight;
    private String numFlight;
    private Airport airportDeparture;
    private Airport airportArrival;
    private Date departureDate;
    private Time departureTime;
    private String aircraftName;

    public FlightClientDTO() {
    }

    public List<FlightClientDTO> flightListToFlightClientDTOList(List<Flight> flights) {
        if (flights == null) {
            return null;
        }

        List<FlightClientDTO> flightClientDTOS = new ArrayList<FlightClientDTO>();

        for (Flight flight : flights) {

            flightClientDTOS.add(addValuesInFlightClientDTO(flight));
        }
        return flightClientDTOS;
    }

    public FlightClientDTO flightToFlightClientDTO(Flight flight) {
        if (flight == null) {
            return null;
        }
        return addValuesInFlightClientDTO(flight);
    }


    private FlightClientDTO addValuesInFlightClientDTO(Flight flight) {
        FlightClientDTO flightClientDTO = new FlightClientDTO();
        flightClientDTO.setIdFlight(flight.getIdFlight());
        flightClientDTO.setAircraftName(flight.getAircraftName());
        flightClientDTO.setAirportDeparture(flight.getAirportDeparture());
        flightClientDTO.setAirportArrival(flight.getAirportArrival());
        flightClientDTO.setNumFlight(flight.getNumFlight());
        flightClientDTO.setDepartureDate(flight.getDepartureDate());
        flightClientDTO.setDepartureTime(flight.getDepartureTime());
        return flightClientDTO;
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

    public Airport getAirportDeparture() {
        return airportDeparture;
    }

    public void setAirportDeparture(Airport airportDeparture) {
        this.airportDeparture = airportDeparture;
    }

    public Airport getAirportArrival() {
        return airportArrival;
    }

    public void setAirportArrival(Airport airportArrival) {
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

    public String getAircraftName() {
        return aircraftName;
    }

    public void setAircraftName(String aircraftName) {
        this.aircraftName = aircraftName;
    }
}
