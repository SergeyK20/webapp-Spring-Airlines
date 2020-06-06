package com.example.airlines.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Airport")
public class Airport {
    private int id;
    private String nameAirport;
    private City airportInTheCity;
    private Set<Flight> flightDepartureSet;
    private Set<Flight> flightArrivalSet;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    @Column(name = "Name_airport")
    @NotNull
    public String getNameAirport() {
        return nameAirport;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Id_city_airport")
    @NotNull
    public City getAirportInTheCity() {
        return airportInTheCity;
    }

    @OneToMany(mappedBy = "airportDeparture", cascade = CascadeType.REMOVE, orphanRemoval = true)
    public Set<Flight> getFlightDepartureSet() {
        return flightDepartureSet;
    }

    @OneToMany(mappedBy = "airportArrival", cascade = CascadeType.REMOVE, orphanRemoval = true)
    public Set<Flight> getFlightArrivalSet() {
        return flightArrivalSet;
    }

    public void setFlightDepartureSet(Set<Flight> flightDepartureSet) {
        this.flightDepartureSet = flightDepartureSet;
    }

    public void setFlightArrivalSet(Set<Flight> flightArrivalSet) {
        this.flightArrivalSet = flightArrivalSet;
    }

    public void setId(int id_airport) {
        this.id = id_airport;
    }

    public void setNameAirport(String name_airport) {
        this.nameAirport = name_airport;
    }

    public void setAirportInTheCity(City airport_city) {
        this.airportInTheCity = airport_city;
    }

}
