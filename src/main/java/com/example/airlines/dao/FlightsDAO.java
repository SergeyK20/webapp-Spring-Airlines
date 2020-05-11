package com.example.airlines.dao;

import com.example.airlines.model.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FlightsDAO extends JpaRepository<Flights, Integer> {

    @Override
    List<Flights> findAll();

    @Override
    java.util.Optional<Flights> findById(Integer Id);

    @Override
    Flights save(Flights flight);

    @Override
    void deleteById(Integer id);
}
