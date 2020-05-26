
package com.example.airlines.service;

import com.example.airlines.dao.AccountUserDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private AccountUserDAO accountUserDAO;

    public UserService(AccountUserDAO accountUserDAO) {
        this.accountUserDAO = accountUserDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return accountUserDAO.findByLogin(login);
    }
}

