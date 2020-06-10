package com.example.airlines.parser;

import com.example.airlines.model.Airport;
import com.example.airlines.model.City;
import org.json.JSONException;
import org.json.JSONObject;

public class AirportParser {
    public static Airport airportParser(String airportString) throws JSONException {
        Airport airport = new Airport();
        JSONObject jsonObject = new JSONObject(airportString);
        String nameAirport = jsonObject.getString("nameAirport");
        JSONObject cityJsonObject = new JSONObject((String)jsonObject.get("airportInTheCity"));
        int idCity = cityJsonObject.getInt("id");
        airport.setNameAirport(nameAirport);
        airport.setAirportInTheCity(new City());
        airport.getAirportInTheCity().setId(idCity);
        return airport;
    }

}
