package com.example.airlines.dao;

import com.example.airlines.model.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface AccountUserDAO extends JpaRepository<AccountUser, Integer> {

    @Override
    List<AccountUser> findAll();

    @Override
    Optional<AccountUser> findById(Integer integer);

    @Query("Select A from AccountUser A where A.login = :login")
    AccountUser findByLogin(@Param("login") String login);
}
