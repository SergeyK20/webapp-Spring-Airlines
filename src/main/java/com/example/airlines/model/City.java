package com.example.airlines.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Table(name = "City")
public class City {
    private int id;
    private String nameCity;
    private Set<Airport> airports;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    @Column(name = "Name_city")
    @NotNull
    public String getNameCity() {
        return nameCity;
    }

    @OneToMany(mappedBy = "airportInTheCity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    public Set<Airport> getAirports() {
        return airports;
    }

    public void setId(int idCity) {
        this.id = idCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public void setAirports(Set<Airport> airports) {
        this.airports = airports;
    }
}
