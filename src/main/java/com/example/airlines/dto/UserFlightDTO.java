package com.example.airlines.dto;


import com.example.airlines.model.UserFlight;

import java.util.ArrayList;
import java.util.List;

/**
 * Список броннированных рейсов с аккаунтами
 */
public class UserFlightDTO {
    private int id;
    private FlightRoleUserDTO flightRoleUserDTO;
    private AccountRoleUserDTO accountRoleUserDTO;
    private boolean payment;

    public UserFlightDTO() {
        flightRoleUserDTO = new FlightRoleUserDTO();
        accountRoleUserDTO = new AccountRoleUserDTO();
    }


    public List<UserFlightDTO> userFlightListInUserFlightDTOList(List<UserFlight> userFlights) {
        if (userFlights == null) {
            return null;
        }
        List<UserFlightDTO> userFlightDTOS = new ArrayList<>();

        for (UserFlight element : userFlights) {
            if(!element.isPayment()) {
                userFlightDTOS.add(new UserFlightDTO().userFlightInUserFlightDTO(element));
            }
        }

        return userFlightDTOS;
    }

    public UserFlightDTO userFlightInUserFlightDTO(UserFlight element) {
        UserFlightDTO userFlightDTO = new UserFlightDTO();
        userFlightDTO.setId(element.getId());
        userFlightDTO.setFlightRoleUserDTO(flightRoleUserDTO.flightToFlightClientDTO(element.getFlight()));
        userFlightDTO.setAccountRoleUserDTO(accountRoleUserDTO.accountUserToAccountDTO(element.getUser()));
        userFlightDTO.setPayment(element.isPayment());
        return userFlightDTO;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FlightRoleUserDTO getFlightRoleUserDTO() {
        return flightRoleUserDTO;
    }

    public void setFlightRoleUserDTO(FlightRoleUserDTO flightRoleUserDTO) {
        this.flightRoleUserDTO = flightRoleUserDTO;
    }

    public AccountRoleUserDTO getAccountRoleUserDTO() {
        return accountRoleUserDTO;
    }

    public void setAccountRoleUserDTO(AccountRoleUserDTO accountRoleUserDTO) {
        this.accountRoleUserDTO = accountRoleUserDTO;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }
}
