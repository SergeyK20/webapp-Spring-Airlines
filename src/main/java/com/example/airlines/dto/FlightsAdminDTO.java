package com.example.airlines.dto;

import com.example.airlines.model.Account;
import com.example.airlines.model.Airport;
import com.example.airlines.model.Flight;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс рейса с аккаунтами, которые забронировали рейс, для администратора
 */
public class FlightsAdminDTO {
    private int idFlight;
    private String numFlight;
    private Airport airportDeparture;
    private Airport airportArrival;
    private Date departureDate;
    private Time departureTime;
    private String aircraftName;
    private List<AccountAdminDTO> accounts;

    @Transactional
    public List<FlightsAdminDTO> flightListToFlightsDTOList(List<Flight> flights) {

        if (flights == null) {
            return null;
        }

        List<FlightsAdminDTO> flightsAdminDTO = new ArrayList<FlightsAdminDTO>();

        for (Flight flight : flights) {
            FlightsAdminDTO flightDTO = new FlightsAdminDTO();
            if (flight.getAccounts() != null) {
                List<AccountAdminDTO> accountsDTO = new ArrayList<AccountAdminDTO>();
                for (Account account : flight.getAccounts()) {
                    accountsDTO.add(addValueAccountsInFlightsDTO(account));
                }
                flightDTO = addValuesInFlightsDTO(flight, accountsDTO);
            }

            flightsAdminDTO.add(flightDTO);
        }

        return flightsAdminDTO;
    }

    public FlightsAdminDTO flightToFlightsDTO(Flight flight) {
        if (flight == null) {
            return null;
        }
        if (flight.getAccounts() != null) {
            List<AccountAdminDTO> accountsDTO = new ArrayList<AccountAdminDTO>();
            for (Account account : flight.getAccounts()) {
                accountsDTO.add(addValueAccountsInFlightsDTO(account));
            }
            return addValuesInFlightsDTO(flight, accountsDTO);
        }
        return addValuesInFlightsDTO(flight, null);
    }

    private AccountAdminDTO addValueAccountsInFlightsDTO(Account account) {
        AccountAdminDTO accountAdminDTO = new AccountAdminDTO();
        accountAdminDTO.setIdAccount(account.getIdAccount());
        accountAdminDTO.setLogin(account.getLogin());
        accountAdminDTO.setPasswordAccount(account.getPasswordAccount());
        accountAdminDTO.setEmail(account.getEmail());
        accountAdminDTO.setAccess(account.getAccess());
        return accountAdminDTO;
    }

    private FlightsAdminDTO addValuesInFlightsDTO(Flight flight, List<AccountAdminDTO> accountsDTO) {
        FlightsAdminDTO flightsAdminDTO = new FlightsAdminDTO();
        flightsAdminDTO.setIdFlight(flight.getIdFlight());
        flightsAdminDTO.setAircraftName(flight.getAircraftName());
        flightsAdminDTO.setAirportDeparture(flight.getAirportDeparture());
        flightsAdminDTO.setAirportArrival(flight.getAirportArrival());
        flightsAdminDTO.setNumFlight(flight.getNumFlight());
        flightsAdminDTO.setDepartureDate(flight.getDepartureDate());
        flightsAdminDTO.setDepartureTime(flight.getDepartureTime());
        flightsAdminDTO.setAccounts(accountsDTO);
        return flightsAdminDTO;
    }

    public FlightsAdminDTO() {
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public String getNumFlight() {
        return numFlight;
    }

    public void setNumFlight(String numFlight) {
        this.numFlight = numFlight;
    }

    public Airport getAirportDeparture() {
        return airportDeparture;
    }

    public void setAirportDeparture(Airport airportDeparture) {
        this.airportDeparture = airportDeparture;
    }

    public Airport getAirportArrival() {
        return airportArrival;
    }

    public void setAirportArrival(Airport airportArrival) {
        this.airportArrival = airportArrival;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public String getAircraftName() {
        return aircraftName;
    }

    public void setAircraftName(String aircraftName) {
        this.aircraftName = aircraftName;
    }

    public List<AccountAdminDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountAdminDTO> accounts) {
        this.accounts = accounts;
    }
}
