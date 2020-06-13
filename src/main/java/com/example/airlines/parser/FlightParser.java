package com.example.airlines.parser;

import com.example.airlines.model.Aircraft;
import com.example.airlines.model.Airport;
import com.example.airlines.model.Flight;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FlightParser {

    public static Flight flightParser(String flightString) throws JSONException {
        Flight flight = new Flight();
        JSONObject jsonObject = new JSONObject(flightString);
        flight.setNumFlight(jsonObject.getString("numFlight"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        flight.setDepartureDate(LocalDate.parse(jsonObject.getString("departureDate"), formatter));
        flight.setDepartureTime(LocalTime.parse(jsonObject.getString("departureTime")));
        flight.setPrice(jsonObject.getInt("price"));
        flight.setAirportDeparture(new Airport());
        JSONObject jsonAirportDep = new JSONObject(jsonObject.getString("airportDeparture"));
        flight.getAirportDeparture().setId(jsonAirportDep.getInt("id"));
        flight.setAirportArrival(new Airport());
        JSONObject jsonAirportArr = new JSONObject(jsonObject.getString("airportArrival"));
        flight.getAirportArrival().setId(jsonAirportArr.getInt("id"));
        flight.setAircraft(new Aircraft());
        JSONObject jsonAircraft = new JSONObject(jsonObject.getString("aircraft"));
        flight.getAircraft().setId(jsonAircraft.getInt("id"));
        return flight;
    }
}
