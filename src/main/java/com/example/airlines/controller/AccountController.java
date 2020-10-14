package com.example.airlines.controller;

import com.example.airlines.dao.AccountUserDAO;
import com.example.airlines.dto.AccountRoleUserDTO;
import com.example.airlines.dto.RoleDTO;
import com.example.airlines.exceptions.CreateException;
import com.example.airlines.exceptions.ExceptionWhenWorkingWithDB;
import com.example.airlines.exceptions.IdSearchException;
import com.example.airlines.model.AccountUser;
import com.example.airlines.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(value = "/accounts")
@PreAuthorize("hasAuthority('ADMIN')")
public class AccountController {

    private AccountUserDAO accountUserDAO;

    private AccountRoleUserDTO accountRoleUserDTO;


    @Autowired
    public AccountController(AccountUserDAO accountUserDAO, AccountRoleUserDTO accountRoleUserDTO) {
        this.accountUserDAO = accountUserDAO;
        this.accountRoleUserDTO = accountRoleUserDTO;
    }

    @GetMapping
    public List<AccountRoleUserDTO> findAccounts() {
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

    /**
     * Пример:
     * {
     *     "role" : "ADMIN"
     * }
     * Метод не добавляет, а изменет значеи роли пользователя
     */
    @PutMapping("/roleUpdate")
    public void roleUpdate(@AuthenticationPrincipal AccountUser user, @RequestBody RoleDTO role){
        try {
            if (accountUserDAO.findById(user.getId()).isPresent()) {
                accountUserDAO.findById(user.getId()).map(accountUser -> {
                    Set<Role> roleSet = new HashSet<>();
                    roleSet.add(Role.valueOf(role.getRole()));
                    accountUser.setRoles(roleSet);
                    return accountUserDAO.save(accountUser);
                });
            } else {
                throw new IdSearchException("Did not found element on update");
            }
        } catch (Exception ex){
            throw new CreateException("Role not updated");
        }
    }
    @PutMapping("/updateAccess/{Id}")
    public void updateAccess(@PathVariable("Id") int id){
        RoleDTO roleDTO = new RoleDTO();roleDTO.setRole(String.valueOf(Role.ADMIN));
        try {
            if (accountUserDAO.findById(id).isPresent()) {
                accountUserDAO.findById(id).map(accountUser -> {
                    Set<Role> roleSet = new HashSet<>();
                    roleSet.add(Role.valueOf(roleDTO.getRole()));
                    accountUser.setRoles(roleSet);
                    return accountUserDAO.save(accountUser);
                });
            } else {
                throw new IdSearchException("Did not found element on update");
            }
        } catch (Exception ex){
            throw new CreateException("Role not updated");
        }
    }
    @PutMapping("/reducingAccess/{Id}")
    public void reducingAccess(@PathVariable("Id") int id){
        RoleDTO roleDTO = new RoleDTO();roleDTO.setRole(String.valueOf(Role.USER));
        try {
            if (accountUserDAO.findById(id).isPresent()) {
                accountUserDAO.findById(id).map(accountUser -> {
                    Set<Role> roleSet = new HashSet<>();
                    roleSet.add(Role.valueOf(roleDTO.getRole()));
                    accountUser.setRoles(roleSet);
                    return accountUserDAO.save(accountUser);
                });
            } else {
                throw new IdSearchException("Did not found element on update");
            }
        } catch (Exception ex){
            throw new CreateException("Role not updated");
        }
    }


}
