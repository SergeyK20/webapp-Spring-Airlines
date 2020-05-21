package com.example.airlines.dto;

import com.example.airlines.model.Access;
import com.example.airlines.model.Account;
import com.example.airlines.model.Flight;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс аккаунта с рейсами, которые забронировал аккаунт, для администратора
 */
@EnableTransactionManagement
public class AccountAdminDTO {
    private int idAccount;
    private String login;
    private String passwordAccount;
    private String email;
    private Access access;
    private List<FlightClientDTO> flights;

    @Transactional
    public List<AccountAdminDTO> accountToAccountDTO(List<Account> accounts) {
        if (accounts == null) {
            return null;
        }

        List<AccountAdminDTO> accountsDTO = new ArrayList<AccountAdminDTO>();

        for (Account account : accounts) {
            AccountAdminDTO accountAdminDTO = new AccountAdminDTO();
            if (account.getFlights() != null) {
                List<FlightClientDTO> flightsDTO = new ArrayList<FlightClientDTO>();
                for (Flight flight : account.getFlights()) {
                    FlightClientDTO flightDTO = new FlightClientDTO();
                    flightDTO.setIdFlight(flight.getIdFlight());
                    flightDTO.setAircraftName(flight.getAircraftName());
                    flightDTO.setAirportDeparture(flight.getAirportDeparture());
                    flightDTO.setAirportArrival(flight.getAirportArrival());
                    flightDTO.setNumFlight(flight.getNumFlight());
                    flightDTO.setDepartureDate(flight.getDepartureDate());
                    flightDTO.setDepartureTime(flight.getDepartureTime());
                    flightsDTO.add(flightDTO);
                }
                accountAdminDTO.setFlights(flightsDTO);
            }
            accountAdminDTO.setIdAccount(account.getIdAccount());
            accountAdminDTO.setLogin(account.getLogin());
            accountAdminDTO.setPasswordAccount(account.getPasswordAccount());
            accountAdminDTO.setEmail(account.getEmail());
            accountAdminDTO.setAccess(account.getAccess());
            accountsDTO.add(accountAdminDTO);
        }

        return accountsDTO;
    }

    public AccountAdminDTO() {
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordAccount() {
        return passwordAccount;
    }

    public void setPasswordAccount(String passwordAccount) {
        this.passwordAccount = passwordAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public List<FlightClientDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightClientDTO> flights) {
        this.flights = flights;
    }
}
