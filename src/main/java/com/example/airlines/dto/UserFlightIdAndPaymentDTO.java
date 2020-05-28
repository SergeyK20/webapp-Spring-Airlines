package com.example.airlines.dto;

/**
 * Класс хранящий в себе значение id, для борирования рейса
 */
public class UserFlightIdAndPaymentDTO {
    private int idUser;
    private int idFlight;
    private boolean payment;

    public UserFlightIdAndPaymentDTO() {
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

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }
}
