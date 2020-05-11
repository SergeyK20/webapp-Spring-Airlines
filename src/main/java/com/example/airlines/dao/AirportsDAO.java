package com.example.airlines.dao;

import com.example.airlines.model.Airports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AirportsDAO extends JpaRepository<Airports,Integer> {
    List<Airports> findAll();
    java.util.Optional<Airports> findById(Integer id);
    Airports save(Airports airport);
    void deleteById(Integer id);

    @Query("Select A from Airports A where A.airportInTheCity.nameCity = :name")
    List<Airports> getByNameCity(@Param("name") String name);
}
