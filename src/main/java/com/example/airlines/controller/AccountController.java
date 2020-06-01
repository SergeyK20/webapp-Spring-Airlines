package com.example.airlines.controller;

import com.example.airlines.dao.AccountUserDAO;
import com.example.airlines.dto.AccountRoleUserDTO;
import com.example.airlines.exceptions.CreateException;
import com.example.airlines.exceptions.ExceptionWhenWorkingWithDB;
import com.example.airlines.exceptions.IdSearchException;
import com.example.airlines.model.AccountUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    private AccountUserDAO accountUserDAO;

    @Autowired
    public AccountController(AccountUserDAO accountUserDAO) {
        this.accountUserDAO = accountUserDAO;
    }

    @GetMapping
    public List<AccountRoleUserDTO> findAccounts() {
        AccountRoleUserDTO accountRoleUserDTO = new AccountRoleUserDTO();
        return accountRoleUserDTO.accountUserListInAccountRoleUserDTOList(accountUserDAO.findAll());
    }


    @GetMapping("{id}")
    public AccountUser findByIdAccounts(@PathVariable("id") int id) {
        if(accountUserDAO.findById(id).isPresent()) {
            return accountUserDAO.findById(id).get();
        } else {
            throw new IdSearchException("No found account...");
        }
    }


    /**
     * Изменение аккаунта со стороны админа
     */
    @PutMapping("/admin/{Id}")
    public void updateAccountAdmin(@PathVariable("Id") int id, @RequestBody AccountUser accountUser) {
            updateAccount(id, accountUser);
    }


    /**
     * Изменение аккаунта со стороны юзера
     */
    @PutMapping("/{Id}")
    public void updateAccountUser(@PathVariable("Id") int id, @RequestBody AccountUser accountUser) {
        updateAccount(id, accountUser);
    }

    /**
     * Сосздание нового аккаунта со стороны админа
     */
    @PostMapping("/admin")
    @ResponseStatus(value = HttpStatus.CREATED)
    public AccountUser saveAccountAdmin(@RequestBody AccountUser accountUser) {
       try {
           return accountUserDAO.save(accountUser);
       } catch (Exception e){
            throw new CreateException("Account not created");
       }
    }

    /**
     * Создание нового аккаунта со стороны юзера
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public AccountUser saveAccountUser(@RequestBody AccountUser accountUser) {
        try {
            return accountUserDAO.save(accountUser);
        } catch (Exception e){
            throw new CreateException("Account not created");
        }
    }

    private void updateAccount(int id, AccountUser accountUser) {
        if(accountUserDAO.findById(id).isPresent()) {
            accountUserDAO.findById(id).map(account1 -> {
                account1.setLogin(accountUser.getLogin());
                account1.setPasswordAccount(accountUser.getPasswordAccount());
                account1.setEmail(accountUser.getEmail());
                account1.setRoles(accountUser.getRoles());
                return accountUserDAO.save(account1);
            });
        } else {
            throw new IdSearchException("Did not found element on update");
        }
    }

    @DeleteMapping("/{Id}")
    public void deleteAccount(@PathVariable("Id") int id) {
        try {
            accountUserDAO.deleteById(id);
        } catch (Exception e){
            throw new ExceptionWhenWorkingWithDB("Error when deleted element");
        }
    }
}
