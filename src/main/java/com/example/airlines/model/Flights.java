package com.example.airlines.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity(name = "Flights")
public class Flights {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_flight")
    private int idFlight;
    @Column(name = "Num_flight")
    private String numFlight;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Id_airport_of_departure")
    private Airports airportsDeparture;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Id_airport_of_arrival")
    private Airports airportsArrival;
    @Column(name = "Departure_date")
    private Date departureDate;
    @Column(name = "Departure_time")
    private Time departureTime;
    @Column(name = "Aircraft_name")
    private  String aircraftName;


    public int getIdFlight() {
        return idFlight;
    }


    public String getNumFlight() {
        return numFlight;
    }


    public Airports getAirportsDeparture() {
        return airportsDeparture;
    }


    public Airports getAirportsArrival() {
        return airportsArrival;
    }


    public Date getDepartureDate() {
        return departureDate;
    }


    public Time getDepartureTime() {
        return departureTime;
    }


    public String getAircraftName() {
        return aircraftName;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public void setNumFlight(String numFlight) {
        this.numFlight = numFlight;
    }

    public void setAirportsDeparture(Airports airportsDeparture) {
        this.airportsDeparture = airportsDeparture;
    }

    public void setAirportsArrival(Airports airportsArrival) {
        this.airportsArrival = airportsArrival;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public void setAircraftName(String aircraftName) {
        this.aircraftName = aircraftName;
    }

    public String toString(){
        return "Flight "+idFlight+":\n"+
                "{\n"+
                    "\tNumFlight: "+numFlight+"\n"+
                    "\tAirportDeparture: "+airportsDeparture+"\n"+
                    "\tAirportDeparture: "+airportsArrival+"\n"+
                    "\tDate: "+departureDate+"\n"+
                    "\tTime: "+departureTime+"\n"+
                    "\tAircraft : "+aircraftName+"\n"+
                "}\n";
    }
}
