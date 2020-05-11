package com.example.airlines.dto;

import com.example.airlines.model.Airports;
import com.example.airlines.model.Flights;

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
    private Airports airportsDeparture;
    private Airports airportsArrival;
    private Date departureDate;
    private Time departureTime;
    private String aircraftName;

    public FlightClientDTO() {
    }

    public List<FlightClientDTO> flightListToFlightClientDTOList(List<Flights> flights) {
        if (flights == null) {
            return null;
        }

        List<FlightClientDTO> flightClientDTOS = new ArrayList<FlightClientDTO>();

        for (Flights flight : flights) {

            flightClientDTOS.add(addValuesInFlightClientDTO(flight));
        }
        return flightClientDTOS;
    }

    public FlightClientDTO flightToFlightClientDTO(Flights flight) {
        if (flight == null) {
            return null;
        }
        return addValuesInFlightClientDTO(flight);
    }


    private FlightClientDTO addValuesInFlightClientDTO(Flights flight) {
        FlightClientDTO flightClientDTO = new FlightClientDTO();
        flightClientDTO.setIdFlight(flight.getIdFlight());
        flightClientDTO.setAircraftName(flight.getAircraftName());
        flightClientDTO.setAirportsDeparture(flight.getAirportsDeparture());
        flightClientDTO.setAirportsArrival(flight.getAirportsArrival());
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

    public Airports getAirportsDeparture() {
        return airportsDeparture;
    }

    public void setAirportsDeparture(Airports airportsDeparture) {
        this.airportsDeparture = airportsDeparture;
    }

    public Airports getAirportsArrival() {
        return airportsArrival;
    }

    public void setAirportsArrival(Airports airportsArrival) {
        this.airportsArrival = airportsArrival;
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
