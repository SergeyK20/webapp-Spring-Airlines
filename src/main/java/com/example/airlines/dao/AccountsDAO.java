package com.example.airlines.dao;

import com.example.airlines.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface AccountsDAO extends JpaRepository<Account, Integer> {

    @Override
    List<Account> findAll();

    @Override
    Optional<Account> findById(Integer integer);
}
