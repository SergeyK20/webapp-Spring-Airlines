package com.example.airlines.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

@Entity(name = "Flights")
public class Flights {

    private int idFlight;
    private String numFlight;
    private Airports airportsDeparture;
    private Airports airportsArrival;
    private Date departureDate;
    private Time departureTime;
    private String aircraftName;
    private Set<Accounts> accounts;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_flight")
    public int getIdFlight() {
        return idFlight;
    }

    @Column(name = "Num_flight")
    public String getNumFlight() {
        return numFlight;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Id_airport_of_departure")
    public Airports getAirportsDeparture() {
        return airportsDeparture;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Id_airport_of_arrival")
    public Airports getAirportsArrival() {
        return airportsArrival;
    }

    @Column(name = "Departure_date")
    public Date getDepartureDate() {
        return departureDate;
    }

    @Column(name = "Departure_time")
    public Time getDepartureTime() {
        return departureTime;
    }

    @Column(name = "Aircraft_name")
    public String getAircraftName() {
        return aircraftName;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "Accounts_flights",
            joinColumns = @JoinColumn(name = "Id_flight_account"),
            inverseJoinColumns = @JoinColumn(name = "Id_account_flight")
    )
    public Set<Accounts> getAccounts() {
        return accounts;
    }

    public void addAccount(Accounts account) {
        accounts.add(account);
    }

    public void removeAccount(Accounts account) {
        accounts.remove(account);
    }

    public void setAccounts(Set<Accounts> accounts) {
        this.accounts = accounts;
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

    public String toString() {
        return "Flight " + idFlight + ":\n" +
                "{\n" +
                "\tNumFlight: " + numFlight + "\n" +
                "\tAirportDeparture: " + airportsDeparture + "\n" +
                "\tAirportDeparture: " + airportsArrival + "\n" +
                "\tDate: " + departureDate + "\n" +
                "\tTime: " + departureTime + "\n" +
                "\tAircraft : " + aircraftName + "\n" +
                "}\n";
    }
}
