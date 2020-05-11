package com.example.airlines.controller;

import com.example.airlines.dao.AccountsDAO;
import com.example.airlines.dao.FlightsDAO;
import com.example.airlines.dto.AccountAdminDTO;
import com.example.airlines.model.Accounts;
import com.example.airlines.model.Flights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/accounts")
public class AccountsController {

    private AccountsDAO accountsDAO;
    private FlightsDAO flightsDAO;

    @Autowired
    public AccountsController(AccountsDAO accountsDAO, FlightsDAO flightsDAO) {
        this.accountsDAO = accountsDAO;
        this.flightsDAO = flightsDAO;
    }

    @GetMapping
    public List<AccountAdminDTO> findAccounts() {
        AccountAdminDTO accountAdminDTO = new AccountAdminDTO();
        return accountAdminDTO.accountToAccountDTO(accountsDAO.findAll());
    }

    @GetMapping("{id}")
    public Accounts findByIdAccounts(@PathVariable("id") int id) {
        return accountsDAO.findById(id).get();
    }

    /**
     * @param id     аккиунта который хочет зарегистрировать рейс
     * @param flight который нудно зарегистрировать
     */
    @PostMapping("/registration/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registrationFlight(@PathVariable("id") int id, @RequestBody Flights flight) {
        if (!flightsDAO.findById(flight.getIdFlight()).isPresent()) {
            // ошибка, рейса под таким id нет
        } else {
            Flights flights = flightsDAO.findById(flight.getIdFlight()).get();
            findByIdAccounts(id).addFlight(flights);
            accountsDAO.save(findByIdAccounts(id));
        }
    }

    /**
     * @param id     аккаунта, который собрался удалять регистрацию
     * @param flight нужно удалить
     */
    @DeleteMapping("/registration/{id}")
    public void removeRegistrationFlight(@PathVariable("id") int id, @RequestBody Flights flight) {
        if (!flightsDAO.findById(flight.getIdFlight()).isPresent()) {
            // ошибка, рейса под таким id нет
        } else {
            Flights flights = flightsDAO.findById(flight.getIdFlight()).get();
            findByIdAccounts(id).removeFlight(flights);
            accountsDAO.save(findByIdAccounts(id));
        }
    }


}
