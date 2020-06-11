
package com.example.airlines.service;

import com.example.airlines.dao.AccountUserDAO;
import com.example.airlines.model.AccountUser;
import com.example.airlines.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private AccountUserDAO accountUserDAO;

    public UserService(AccountUserDAO accountUserDAO) {
        this.accountUserDAO = accountUserDAO;
    }

    public AccountUser save(AccountUser accountUser){
        AccountUser newUser = new AccountUser();
        newUser.setLogin(accountUser.getLogin());
        newUser.setPasswordAccount(bCryptPasswordEncoder.encode(accountUser.getPassword()));
        newUser.setRoles(Collections.singleton(Role.USER));
        newUser.setEmail(accountUser.getEmail());
        newUser.setActive(accountUser.isActive());
        accountUserDAO.save(newUser);
        return newUser;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return accountUserDAO.findByLogin(login);
    }
}

