package com.example.airlines.model;

import javax.persistence.*;

    @Entity(name = "Airports")
    public class Airports {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "Id_airport")
        private int idAirport;
        @Column(name = "Name_airport")
        private String nameAirport;
        @ManyToOne(cascade = CascadeType.MERGE)
        @JoinColumn(name = "Id_city_airport")
        private City airportInTheCity;


        public int getIdAirport() {
            return idAirport;
        }


        public String getNameAirport() {
            return nameAirport;
        }


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
