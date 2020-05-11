package com.example.airlines.dto;

import com.example.airlines.model.Accounts;
import com.example.airlines.model.Airports;
import com.example.airlines.model.Flights;
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
    private Airports airportsDeparture;
    private Airports airportsArrival;
    private Date departureDate;
    private Time departureTime;
    private String aircraftName;
    private List<AccountAdminDTO> accounts;

    @Transactional
    public List<FlightsAdminDTO> flightListToFlightsDTOList(List<Flights> flights) {

        if (flights == null) {
            return null;
        }

        List<FlightsAdminDTO> flightsAdminDTO = new ArrayList<FlightsAdminDTO>();

        for (Flights flight : flights) {
            FlightsAdminDTO flightDTO = new FlightsAdminDTO();
            if (flight.getAccounts() != null) {
                List<AccountAdminDTO> accountsDTO = new ArrayList<AccountAdminDTO>();
                for (Accounts account : flight.getAccounts()) {
                    accountsDTO.add(addValueAccountsInFlightsDTO(account));
                }
                flightDTO = addValuesInFlightsDTO(flight, accountsDTO);
            }

            flightsAdminDTO.add(flightDTO);
        }

        return flightsAdminDTO;
    }

    public FlightsAdminDTO flightToFlightsDTO(Flights flight) {
        if (flight == null) {
            return null;
        }
        if (flight.getAccounts() != null) {
            List<AccountAdminDTO> accountsDTO = new ArrayList<AccountAdminDTO>();
            for (Accounts account : flight.getAccounts()) {
                accountsDTO.add(addValueAccountsInFlightsDTO(account));
            }
            return addValuesInFlightsDTO(flight, accountsDTO);
        }
        return addValuesInFlightsDTO(flight, null);
    }

    private AccountAdminDTO addValueAccountsInFlightsDTO(Accounts account) {
        AccountAdminDTO accountAdminDTO = new AccountAdminDTO();
        accountAdminDTO.setIdAccount(account.getIdAccount());
        accountAdminDTO.setLogin(account.getLogin());
        accountAdminDTO.setPasswordAccount(account.getPasswordAccount());
        accountAdminDTO.setEmail(account.getEmail());
        accountAdminDTO.setAccess(account.getAccess());
        return accountAdminDTO;
    }

    private FlightsAdminDTO addValuesInFlightsDTO(Flights flight, List<AccountAdminDTO> accountsDTO) {
        FlightsAdminDTO flightsAdminDTO = new FlightsAdminDTO();
        flightsAdminDTO.setIdFlight(flight.getIdFlight());
        flightsAdminDTO.setAircraftName(flight.getAircraftName());
        flightsAdminDTO.setAirportsDeparture(flight.getAirportsDeparture());
        flightsAdminDTO.setAirportsArrival(flight.getAirportsArrival());
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

    public Airports getAirportsDeparture() {
        return airportsDeparture;
    }

    public void setAirportsDeparture(Airports airportsDeparture) {
        this.airportsDeparture = airportsDeparture;
    }

    public Airports getAirportsArrival() {
        return airportsArrival;
    }

    public void setAirportsArrival(Airports airportsArrival) {
        this.airportsArrival = airportsArrival;
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
