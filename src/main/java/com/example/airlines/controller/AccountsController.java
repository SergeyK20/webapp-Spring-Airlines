package com.example.airlines.controller;

import com.example.airlines.dao.AccountsDAO;
import com.example.airlines.dao.FlightsDAO;
import com.example.airlines.dto.AccountAdminDTO;
import com.example.airlines.model.Account;
import com.example.airlines.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AccountAdminDTO> findAccounts() {
        AccountAdminDTO accountAdminDTO = new AccountAdminDTO();
        return accountAdminDTO.accountToAccountDTO(accountsDAO.findAll());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Account findByIdAccounts(@PathVariable("id") int id) {
        return accountsDAO.findById(id).get();
    }


    /**
     * Изменение аккаунта со стороны админа
     */
    @PutMapping("/admin/{Id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateAccountAdmin(@PathVariable("Id") int id, @RequestBody Account account){
        updateAccount(id, account);
    }


    /**
     * Изменение аккаунта со стороны юзера
     */
    @PutMapping("/{Id}")
    @PreAuthorize("hasAuthority('USER')")
    public void updateAccountUser(@PathVariable("Id") int id, @RequestBody Account account){
        updateAccount(id, account);
    }

    /**
     * Сосздание нового аккаунта со стороны админа
     */
    @PostMapping("/admin")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Account saveAccountAdmin(@RequestBody Account account) {
        return accountsDAO.save(account);
    }

    /**
     * Создание нового аккаунта со стороны юзера
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('USER')")
    public Account saveAccountUser(@RequestBody Account account) {
        return accountsDAO.save(account);
    }

    private void updateAccount(int id, Account account){
        accountsDAO.findById(id).map(account1 -> {
                                    account1.setLogin(account.getLogin());
                                    account1.setPasswordAccount(account.getPasswordAccount());
                                    account1.setEmail(account.getEmail());
                                    account1.setAccess(account.getAccess());
                                    return accountsDAO.save(account1);
        });
    }

    @DeleteMapping("/{Id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteAccount(@PathVariable("Id") int id) {
        accountsDAO.deleteById(id);
    }


    /**
     * @param id     аккиунта который хочет зарегистрировать рейс
     * @param flight который нудно зарегистрировать
     */
    @PostMapping("/registration/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('USER')")
    public void registrationFlight(@PathVariable("id") int id, @RequestBody Flight flight) {
        if (!flightsDAO.findById(flight.getIdFlight()).isPresent()) {
            // ошибка, рейса под таким id нет
        } else {
            Flight flights = flightsDAO.findById(flight.getIdFlight()).get();
            findByIdAccounts(id).addFlight(flights);
            accountsDAO.save(findByIdAccounts(id));
        }
    }

    /**
     * @param id     аккаунта, который собрался удалять регистрацию
     * @param flight нужно удалить
     */
    @DeleteMapping("/registration/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public void removeRegistrationFlight(@PathVariable("id") int id,@Valid @RequestBody Flight flight) {
        if (!flightsDAO.findById(flight.getIdFlight()).isPresent()) {
            // ошибка, рейса под таким id нет
        } else {
            Flight flights = flightsDAO.findById(flight.getIdFlight()).get();
            findByIdAccounts(id).removeFlight(flights);
            accountsDAO.save(findByIdAccounts(id));
        }
    }


}
