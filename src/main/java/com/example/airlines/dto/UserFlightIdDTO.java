package com.example.airlines.dto;

/**
 * Класс хранящий в себе значение id, для борирования рейса
 */
public class UserFlightIdDTO {
    private int idUser;
    private int idFlight;

    public UserFlightIdDTO() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }
}
