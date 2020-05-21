package com.example.airlines.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

@Entity(name = "Flights")
public class Flight {

    private int idFlight;
    private String numFlight;
    private Airport airportDeparture;
    private Airport airportArrival;
    private Date departureDate;
    private Time departureTime;
    private String aircraftName;
    private Set<Account> accounts;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_flight")
    public int getIdFlight() {
        return idFlight;
    }

    @Column(name = "Num_flight")
    @NotNull
    public String getNumFlight() {
        return numFlight;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Id_airport_of_departure")
    @NotNull
    public Airport getAirportDeparture() {
        return airportDeparture;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Id_airport_of_arrival")
    @NotNull
    public Airport getAirportArrival() {
        return airportArrival;
    }

    @Column(name = "Departure_date")
    @NotNull
    public Date getDepartureDate() {
        return departureDate;
    }

    @Column(name = "Departure_time")
    @NotNull
    public Time getDepartureTime() {
        return departureTime;
    }

    @Column(name = "Aircraft_name")
    @NotNull
    public String getAircraftName() {
        return aircraftName;
    }


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "Accounts_flights",
            joinColumns = @JoinColumn(name = "Id_flight_account"),
            inverseJoinColumns = @JoinColumn(name = "Id_account_flight")
    )
    @Size(max = 1)
    public Set<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public void setNumFlight(String numFlight) {
        this.numFlight = numFlight;
    }

    public void setAirportDeparture(Airport airportDeparture) {
        this.airportDeparture = airportDeparture;
    }

    public void setAirportArrival(Airport airportArrival) {
        this.airportArrival = airportArrival;
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
                "\tAirportDeparture: " + airportDeparture + "\n" +
                "\tAirportDeparture: " + airportArrival + "\n" +
                "\tDate: " + departureDate + "\n" +
                "\tTime: " + departureTime + "\n" +
                "\tAircraft : " + aircraftName + "\n" +
                "}\n";
    }
}
