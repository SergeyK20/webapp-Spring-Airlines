package com.example.airlines.dao;

import com.example.airlines.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FlightDAO extends JpaRepository<Flight, Integer> {

    @Override
    List<Flight> findAll();

    @Override
    java.util.Optional<Flight> findById(Integer Id);

    @Override
    Flight save(Flight flight);

    @Override
    void deleteById(Integer id);

    @Query("Select f from Flight f where f.airportArrival.airportInTheCity.nameCity = :city")
    List<Flight> findByCityArrival(@Param("city") String city);

    @Query("Select f from Flight f where f.airportDeparture.airportInTheCity.nameCity = :city")
    List<Flight> findByCityDeparture(@Param("city") String city);

    @Query("Select f from Flight f where f.airportArrival.nameAirport like (:str) or " +
            "                            f.airportDeparture.nameAirport like (:str) or " +
            "                            f.aircraft.nameAircraft like (:str) or " +
            "                            f.numFlight like (:str) or " +
            "                            f.departureDate like (:str) or " +
            "                            f.departureTime like (:str) or " +
            "                            f.price like (:str)")
    List<Flight> findFlights(@Param("str") String str);
}
