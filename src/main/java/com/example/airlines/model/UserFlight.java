package com.example.airlines.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "User_flight")
public class UserFlight {

    private int id;
    private AccountUser user;
    private Flight flight;
    private boolean payment;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "Id_user")
    @NotNull
    public AccountUser getUser() {
        return user;
    }

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "Id_flight")
    @NotNull
    public Flight getFlight() {
        return flight;
    }

    @NotNull
    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public void setId(int idUserFlight) {
        this.id = idUserFlight;
    }

    public void setUser(AccountUser user) {
        this.user = user;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
