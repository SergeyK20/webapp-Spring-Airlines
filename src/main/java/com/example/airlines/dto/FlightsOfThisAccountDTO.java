package com.example.airlines.dto;

import com.example.airlines.model.UserFlight;

import java.util.ArrayList;
import java.util.List;

public class FlightsOfThisAccountDTO {
    private int id;
    private FlightRoleUserDTO flightRoleUserDTO;
    private boolean payment;

    public FlightsOfThisAccountDTO() {
    }

    public FlightsOfThisAccountDTO(FlightRoleUserDTO flightRoleUserDTO, boolean payment) {
        this.flightRoleUserDTO = flightRoleUserDTO;
        this.payment = payment;
    }

    public FlightRoleUserDTO getFlightRoleUserDTO() {
        return flightRoleUserDTO;
    }

    public List<FlightsOfThisAccountDTO> flightsOfThisAccountDTOList(List<UserFlight> userFlights) {
        if (userFlights == null) {
            return null;
        }
        List<FlightsOfThisAccountDTO> flightsOfThisAccountDTOS= new ArrayList<>();

        for (UserFlight element : userFlights) {
            flightsOfThisAccountDTOS.add(flightsOfThisAccountDTO(element));
        }

        return flightsOfThisAccountDTOS;
    }

    public FlightsOfThisAccountDTO flightsOfThisAccountDTO(UserFlight element) {
        FlightsOfThisAccountDTO flightsOfThisAccountDTO = new FlightsOfThisAccountDTO();
        flightsOfThisAccountDTO.setId(element.getId());
        flightsOfThisAccountDTO.setFlightRoleUserDTO(new FlightRoleUserDTO().flightToFlightClientDTO(element.getFlight()));
        flightsOfThisAccountDTO.setPayment(element.isPayment());
        return flightsOfThisAccountDTO;
    }

    public void setFlightRoleUserDTO(FlightRoleUserDTO flightRoleUserDTO) {
        this.flightRoleUserDTO = flightRoleUserDTO;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
