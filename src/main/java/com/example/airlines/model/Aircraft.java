package com.example.airlines.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Aircraft")
public class Aircraft {

    private int id;
    private String nameAircraft;
    private int numberSeatsAircraft;
    private Set<Flight> flightSet;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    @NotNull
    public String getNameAircraft() {
        return nameAircraft;
    }

    @NotNull
    public int getNumberSeatsAircraft() {
        return numberSeatsAircraft;
    }

    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.REMOVE, orphanRemoval = true)
    public Set<Flight> getFlightSet() {
        return flightSet;
    }

    public void setFlightSet(Set<Flight> flightSet) {
        this.flightSet = flightSet;
    }

    public void setId(int idAircraft) {
        this.id = idAircraft;
    }

    public void setNameAircraft(String nameAircraft) {
        this.nameAircraft = nameAircraft;
    }

    public void setNumberSeatsAircraft(int numberSeatsAircraft) {
        this.numberSeatsAircraft = numberSeatsAircraft;
    }
}
