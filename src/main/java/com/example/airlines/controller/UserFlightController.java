package com.example.airlines.controller;

import com.example.airlines.dao.AccountUserDAO;
import com.example.airlines.dao.FlightDAO;
import com.example.airlines.dao.UserFlightDAO;
import com.example.airlines.dto.UserFlightDTO;
import com.example.airlines.dto.UserFlightIdAndPaymentDTO;
import com.example.airlines.model.AccountUser;
import com.example.airlines.model.Flight;
import com.example.airlines.model.UserFlight;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user_flight")
public class UserFlightController {

    private UserFlightDAO userFlightDAO;
    private AccountUserDAO accountUserDAO;
    private FlightDAO flightDAO;

    public UserFlightController(UserFlightDAO userFlightDAO, AccountUserDAO accountUserDAO, FlightDAO flightDAO) {
        this.userFlightDAO = userFlightDAO;
        this.accountUserDAO = accountUserDAO;
        this.flightDAO = flightDAO;
    }

    @GetMapping
    public List<UserFlightDTO> findAll() {
        UserFlightDTO userFlightDTO = new UserFlightDTO();
        return userFlightDTO.userFlightListInUserFlightDTOList(userFlightDAO.findAll());
    }

    @GetMapping("/flight/{Id}")
    public List<UserFlightDTO> findAllByIdFlight(@PathVariable("Id") int id) {
        UserFlightDTO userFlightDTO = new UserFlightDTO();
        return userFlightDTO.userFlightListInUserFlightDTOList(userFlightDAO.findByFlight(id));
    }

    @GetMapping("/user")

    public List<UserFlightDTO> findAllByIdUser(@AuthenticationPrincipal AccountUser user) {
        UserFlightDTO userFlightDTO = new UserFlightDTO();
        return userFlightDTO.userFlightListInUserFlightDTOList(userFlightDAO.findByUser(user.getId()));
    }

    @PostMapping("/booking")
    public UserFlightDTO saveUserFlight(@RequestBody UserFlightIdAndPaymentDTO element) {
        UserFlightDTO userFlightDTO = new UserFlightDTO();
        UserFlight userFlight = new UserFlight();
        if (!flightDAO.findById(element.getIdFlight()).isPresent() || !accountUserDAO.findById(element.getIdUser()).isPresent()) {
            return null;
        } else {
            Flight flight = flightDAO.findById(element.getIdFlight()).get();
            AccountUser accountUser = accountUserDAO.findById(element.getIdUser()).get();
            userFlight.setUser(accountUser);
            userFlight.setFlight(flight);
            userFlight.setPayment(element.isPayment());
            if (flight.getAccountUsers().size() < flight.getAircraft().getNumberSeatsAircraft()) {
                return userFlightDTO.userFlightInUserFlightDTO(userFlightDAO.save(userFlight));
            } else {
                // переполнение заказов на рейс
                return null;
            }
        }

    }

    @DeleteMapping("/remove_booking/{Id}")
    public void removeUserFlight(@PathVariable("Id") int id) {
        if (userFlightDAO.findById(id).isPresent()) {
            userFlightDAO.delete(userFlightDAO.findById(id).get());
        } else {
            // что то сделать если не нашел
        }
    }

}
