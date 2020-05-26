package com.example.airlines.dao;

import com.example.airlines.model.UserFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserFlightDAO extends JpaRepository<UserFlight, Integer> {

    @Query("Select U_f from UserFlight  U_f where U_f.flight.id = :Id")
    List<UserFlight> findByFlight(@Param("Id") Integer id);

    @Query("Select U_f from UserFlight U_f where U_f.user.id = :Id")
    List<UserFlight> findByUser(@Param("Id") Integer id);
}
