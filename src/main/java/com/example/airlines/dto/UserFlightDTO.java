package com.example.airlines.dto;


import com.example.airlines.model.UserFlight;

import java.util.ArrayList;
import java.util.List;

/**
 * Список броннированных рейсов с аккаунтами
 */
public class UserFlightDTO {
    private FlightRoleUserDTO flightRoleUserDTO;
    private AccountRoleUserDTO accountRoleUserDTO;

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
            userFlightDTOS.add(new UserFlightDTO().userFlightInUserFlightDTO(element));
        }

        return userFlightDTOS;
    }

    public UserFlightDTO userFlightInUserFlightDTO(UserFlight element) {
        UserFlightDTO userFlightDTO = new UserFlightDTO();
        userFlightDTO.setFlightRoleUserDTO(flightRoleUserDTO.flightToFlightClientDTO(element.getFlight()));
        userFlightDTO.setAccountRoleUserDTO(accountRoleUserDTO.accountUserToAccountDTO(element.getUser()));
        return userFlightDTO;
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
}
