package com.example.airlines.model;

import javax.persistence.*;

@Entity(name = "Airports")
public class Airport {

    private int idAirport;
    private String nameAirport;
    private City airportInTheCity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_airport")
    public int getIdAirport() {
        return idAirport;
    }

    @Column(name = "Name_airport")
    public String getNameAirport() {
        return nameAirport;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Id_city_airport")
    public City getAirportInTheCity() {
        return airportInTheCity;
    }

    public void setIdAirport(int id_airport) {
        this.idAirport = id_airport;
    }

    public void setNameAirport(String name_airport) {
        this.nameAirport = name_airport;
    }

    public void setAirportInTheCity(City airport_city) {
        this.airportInTheCity = airport_city;
    }

}
