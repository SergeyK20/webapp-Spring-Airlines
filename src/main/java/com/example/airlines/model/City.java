package com.example.airlines.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "City")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_city")
    private int idCity;
    @Column(name = "Name_city")
    private String nameCity;
    /*private List<Airports> listAirports = new ArrayList<Airports>();*/


    public int getIdCity() {
        return idCity;
    }


    public String getNameCity() {
        return nameCity;
    }


    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

}
