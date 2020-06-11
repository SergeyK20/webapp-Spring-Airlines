package com.example.airlines.dto;

import com.example.airlines.model.UserFlight;

import java.util.ArrayList;
import java.util.List;

public class UsersOfThisFlightDTO {
    private int id;
    private AccountRoleUserDTO accountRoleUserDTO;
    private boolean payment;

    public UsersOfThisFlightDTO() {
    }

    public List<UsersOfThisFlightDTO> usersOfThisFlightDTOList(List<UserFlight> userFlights) {
        if (userFlights == null) {
            return null;
        }
        List<UsersOfThisFlightDTO> userFlightDTOS = new ArrayList<>();

        for (UserFlight element : userFlights) {
            userFlightDTOS.add(usersOfThisFlightDTO(element));
        }

        return userFlightDTOS;
    }

    public UsersOfThisFlightDTO usersOfThisFlightDTO(UserFlight element) {
        UsersOfThisFlightDTO userFlightDTO = new UsersOfThisFlightDTO();
        userFlightDTO.setId(element.getId());
        userFlightDTO.setAccountRoleUserDTO(new AccountRoleUserDTO().accountUserToAccountDTO(element.getUser()));
        userFlightDTO.setPayment(element.isPayment());
        return userFlightDTO;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
