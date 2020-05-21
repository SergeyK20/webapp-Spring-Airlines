package com.example.airlines.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Accounts")
public class Account {

    private int idAccount;
    private String login;
    private String passwordAccount;
    private String email;
    private Access access;
    private List<Flight> flights;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_account")
    public int getIdAccount() {
        return idAccount;
    }

    @Column(name = "Login")
    public String getLogin() {
        return login;
    }

    @Column(name = "Password_account")
    public String getPasswordAccount() {
        return passwordAccount;
    }

    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Id_access_account")
    public Access getAccess() {
        return access;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "Accounts_flights",
            joinColumns = @JoinColumn(name = "Id_account_flight"),
            inverseJoinColumns = @JoinColumn(name = "Id_flight_account")
    )
    public List<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
        flight.addAccount(this);
    }

    public void removeFlight(Flight flight) {
        flights.remove(flight);
        flight.removeAccount(this);
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }


    public void setLogin(String login) {
        this.login = login;
    }


    public void setPasswordAccount(String passwordAccount) {
        this.passwordAccount = passwordAccount;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void setAccess(Access access) {
        this.access = access;
    }
}
