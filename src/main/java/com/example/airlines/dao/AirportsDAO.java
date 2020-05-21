package com.example.airlines.dao;

import com.example.airlines.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AirportsDAO extends JpaRepository<Airport,Integer> {

    @Override
    List<Airport> findAll();

    @Override
    java.util.Optional<Airport> findById(Integer id);

    @Override
    Airport save(Airport airport);

    @Override
    void deleteById(Integer id);

    @Query("Select A from Airports A where A.airportInTheCity.nameCity = :name")
    List<Airport> getByNameCity(@Param("name") String name);
}
