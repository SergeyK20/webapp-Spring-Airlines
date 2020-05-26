package com.example.airlines.dao;

import com.example.airlines.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface AircraftDAO extends JpaRepository<Aircraft, Integer> {

    @Override
    Optional<Aircraft> findById(Integer id);

    @Override
    Aircraft save(Aircraft aircraft);
}
