package com.example.airlines.dao;

import com.example.airlines.model.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FlightsDAO extends JpaRepository<Flights, Integer> {
    List<Flights> findAll();
    java.util.Optional<Flights> findById(Integer Id);
    Flights save(Flights flight);
    void deleteById(Integer id);

   /* @Modifying
    @Query("update Flights f set f.numFlight = :numFlight," +
            "                    f.airportsDeparture.nameAirport = :nameAirportDep," +
            "                    f.airportsDeparture.airportInTheCity.nameCity = :nameAirportCityDep," +
            "                    f.airportsArrival.nameAirport = :nameAirportArr," +
            "                    f.airportsArrival.airportInTheCity.nameCity = :nameAirportCityArr," +
            "                    f.departureDate = :dateDep," +
            "                    f.departureTime = :timeDep," +
            "                    f.aircraftName = :nameAircraft"+
            "                    where f.idFlight = :id")
    void update(@Param("numFlight") String numFlight,
                @Param("nameAirportDep") String nameAirportDep,
                @Param("nameAirportCityDep") String nameAirportCityDep,
                @Param("nameAirportArr") String nameAirportArr,
                @Param("nameAirportCityArr") String nameAirportCityArr,
                @Param("dateDep") Date dateDep,
                @Param("timeDep") Time timeDep,
                @Param("nameAircraft") String nameAircraft,
                @Param("id") Integer id);*/
}
