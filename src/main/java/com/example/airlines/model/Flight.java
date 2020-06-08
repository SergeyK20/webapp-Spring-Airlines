package com.example.airlines.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "Flight")
public class Flight {
    public Flight() {
    }

    private int id;
    private String numFlight;
    private Airport airportDeparture;
    private Airport airportArrival;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private Aircraft aircraft;
    private double price;
    private Set<UserFlight> accountUsers;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    @Column(name = "Num_flight")
    @NotNull
    public String getNumFlight() {
        return numFlight;
    }

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "Id_airport_of_departure")
    @NotNull
    public Airport getAirportDeparture() {
        return airportDeparture;
    }

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "Id_airport_of_arrival")
    @NotNull
    public Airport getAirportArrival() {
        return airportArrival;
    }

    @Column(name = "Departure_date")
    @NotNull
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    @Column(name = "Departure_time")
    @NotNull
    public LocalTime getDepartureTime() {
        return departureTime;
    }

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "Id_aircraft")
    public Aircraft getAircraft() {
        return aircraft;
    }

    @NotNull
    public double getPrice() {
        return price;
    }

    @OneToMany(mappedBy = "flight", cascade = CascadeType.MERGE, orphanRemoval = true)
    public Set<UserFlight> getAccountUsers() {
        return accountUsers;
    }

    public boolean addAccountUsers(UserFlight userFlight){
        return accountUsers.add(userFlight);
    }

    public boolean deleteAccountUser(UserFlight userFlight){
        return accountUsers.remove(userFlight);
    }


    public void setAccountUsers(Set<UserFlight> accountUsers) {
        this.accountUsers = accountUsers;
    }

    public void setId(int idFlight) {
        this.id = idFlight;
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

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
