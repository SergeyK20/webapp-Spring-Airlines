package com.example.airlines.dao;

import com.example.airlines.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
