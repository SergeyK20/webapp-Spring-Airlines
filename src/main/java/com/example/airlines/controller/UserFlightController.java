package com.example.airlines.controller;

import com.example.airlines.dao.AccountUserDAO;
import com.example.airlines.dao.FlightDAO;
import com.example.airlines.dao.UserFlightDAO;
import com.example.airlines.dto.FlightsOfThisAccountDTO;
import com.example.airlines.dto.UserFlightDTO;
import com.example.airlines.dto.FlightIdDTO;
import com.example.airlines.dto.UsersOfThisFlightDTO;
import com.example.airlines.exceptions.CreateException;
import com.example.airlines.exceptions.ExceptionWhenWorkingWithDB;
import com.example.airlines.model.AccountUser;
import com.example.airlines.model.Flight;
import com.example.airlines.model.UserFlight;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * Возвращает все забронированные рейсы и пользователей, котрые забронировали рейс
     */
    @GetMapping("/all")
    public List<UserFlightDTO> findAll() {
        UserFlightDTO userFlightDTO = new UserFlightDTO();
        return userFlightDTO.userFlightListInUserFlightDTOList(userFlightDAO.findAll());
    }

    /**
     * Получаем все аккаунты, которые были зарегистрированные на этот рейс
     * @param id индентификатор рейса
     */
    @GetMapping("/flight/{Id}")
    public List<UsersOfThisFlightDTO> findAllByIdFlight(@PathVariable("Id") int id) {
        UsersOfThisFlightDTO usersOfThisFlightDTO = new UsersOfThisFlightDTO();
        return usersOfThisFlightDTO.usersOfThisFlightDTOList(userFlightDAO.findByFlight(id));
    }


    /**
     * Получаем список забронированных рейсов отдельного пользователя
     * @param user информация о пользователе
     */
    @GetMapping
    public List<FlightsOfThisAccountDTO> findAllByIdUser(@AuthenticationPrincipal AccountUser user) {
        FlightsOfThisAccountDTO flightsOfThisAccountDTO = new FlightsOfThisAccountDTO();
        return flightsOfThisAccountDTO.flightsOfThisAccountDTOList(userFlightDAO.findByUser(user.getId()));
    }

    /**
     * Осуществляет бронь рейса
     * Пример:
     * {
     *     "id": 1
     * }
     */
    @PostMapping("/booking")
    public UserFlightDTO saveUserFlight(@AuthenticationPrincipal AccountUser user,@RequestBody FlightIdDTO element) {
        UserFlightDTO userFlightDTO = new UserFlightDTO();
        UserFlight userFlight = new UserFlight();
        if (!flightDAO.findById(element.getId()).isPresent() || !accountUserDAO.findById(user.getId()).isPresent()) {
            throw  new CreateException("Error creating");
        } else {
            Flight flight = flightDAO.findById(element.getId()).get();
            AccountUser accountUser = accountUserDAO.findById(user.getId()).get();
            userFlight.setUser(accountUser);
            userFlight.setFlight(flight);
            userFlight.setPayment(false);
            if (flight.getAccountUsers().size() < flight.getAircraft().getNumberSeatsAircraft()) {
                return userFlightDTO.userFlightInUserFlightDTO(userFlightDAO.save(userFlight));
            } else {
                // переполнение заказов на рейс
                throw  new CreateException("Error creating");
            }
        }

    }

    /**
     * Пример:
     * {
     *     "id": 1
     * }
     * Удаляет бронь с рейса
     * @param id  элемента в таблице user_flight
     */
    @DeleteMapping("/remove_booking/{id}")
    public void removeUserFlight(@PathVariable("id") int id) {
        if (userFlightDAO.findById(id).isPresent()) {
            userFlightDAO.deleteById(id);
        } else {
            throw new ExceptionWhenWorkingWithDB("Error deleted");
        }
    }

    @PutMapping("/payment/{id}")
    public void paymentFlight(@PathVariable("id") int id){
        if(userFlightDAO.findById(id).isPresent()){
            userFlightDAO.findById(id).map(userFlight -> {
                userFlight.setPayment(true);
                return userFlightDAO.save(userFlight);
            });
        } else {
            throw new ExceptionWhenWorkingWithDB("Error payment");
        }
    }

}
