package com.example.airlines.dao;

import com.example.airlines.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FlightsDAO extends JpaRepository<Flight, Integer> {

    @Override
    List<Flight> findAll();

    @Override
    java.util.Optional<Flight> findById(Integer Id);

    @Override
    Flight save(Flight flight);

    @Override
    void deleteById(Integer id);
}
