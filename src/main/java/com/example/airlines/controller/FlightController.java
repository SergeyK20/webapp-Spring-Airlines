package com.example.airlines.controller;

import com.example.airlines.dao.AircraftDAO;
import com.example.airlines.dao.AirportDAO;
import com.example.airlines.dao.FlightDAO;
import com.example.airlines.dto.FlightRoleUserDTO;
import com.example.airlines.model.Flight;
import com.example.airlines.model.UserFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    private FlightDAO flightDAO;
    private AirportDAO airportDAO;
    private AircraftDAO aircraftDAO;

    @Autowired
    public FlightController(FlightDAO flightDAO, AirportDAO airportDAO, AircraftDAO aircraftDAO) {
        this.airportDAO = airportDAO;
        this.flightDAO = flightDAO;
        this.aircraftDAO = aircraftDAO;
    }

    /**
     * @return возвращает список рейсов без аккаунтов
     */
    @GetMapping
    public List<FlightRoleUserDTO> getAllCityClient() {
        FlightRoleUserDTO flightsDTO = new FlightRoleUserDTO();
        return flightsDTO.flightListToFlightClientDTOList(flightDAO.findAll());
    }


    /**
     * @return возвращает рейс без аккаунта по id
     */
    @GetMapping("/Id/{Id}")
    public FlightRoleUserDTO findFlight(@PathVariable("Id") int id) {
        FlightRoleUserDTO flightRoleUserDTO = new FlightRoleUserDTO();
        if (!flightDAO.findById(id).isPresent()) {
            return null;
        } else {
            return flightRoleUserDTO.flightToFlightClientDTO(flightDAO.findById(id).get());
        }
    }

    /**
     * Фильтрация по городам прибытия
     */
    @GetMapping("/City_arrival/{City}")
    public List<FlightRoleUserDTO> findFlightByCityArrival(@PathVariable("City") String city) {
        FlightRoleUserDTO flightsDTO = new FlightRoleUserDTO();
        return flightsDTO.flightListToFlightClientDTOList(flightDAO.findByCityArrival(city));
    }

    /**
     * Фильтрация по городам отбытия
     */
    @GetMapping("/City_departure/{City}")
    public List<FlightRoleUserDTO> findFlightByCityDeparture(@PathVariable("City") String city) {
        FlightRoleUserDTO flightsDTO = new FlightRoleUserDTO();
        return flightsDTO.flightListToFlightClientDTOList(flightDAO.findByCityDeparture(city));
    }

    /**
     * @param flightBeta {
     *                   "numFlight": "111111",
     *                   "airportDeparture": {
     *                   "id": 1
     *                   },
     *                   "airportArrival": {
     *                   "id": 2
     *                   },
     *                   "departureDate": "2020-05-19",
     *                   "departureTime": "13:00:00",
     *                   "aircraft": {
     *                   "id": 1
     *                   },
     *                   "price": 123132.0
     *                   }
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public FlightRoleUserDTO saveFlight(@Valid @RequestBody Flight flightBeta) {
        FlightRoleUserDTO flightsDTO = new FlightRoleUserDTO();
        Flight flight = setFlight(flightBeta);
        if (flight != null) {
            return flightsDTO.flightToFlightClientDTO(flightDAO.save(flight));
        } else {
            return null;
        }
    }

    /**
     * @param id         id рейса который хотите изменить
     * @param flightBeta {
     *                   "numFlight": "111111",
     *                   "airportDeparture": {
     *                   "id": 1
     *                   },
     *                   "airportArrival": {
     *                   "id": 2
     *                   },
     *                   "departureDate": "2020-05-19",
     *                   "departureTime": "13:00:00",
     *                   "aircraft": {
     *                   "id": 1
     *                   },
     *                   "price": 123132.0
     *                   }
     */
    @PutMapping("/{Id}")
    public void updateFlight(@PathVariable("Id") int id, @RequestBody @Valid Flight flightBeta) {
        flightDAO.findById(id).map(flight -> {
            flightBeta.setId(id);
            flight = setFlight(flightBeta);
            if (flight != null) {
                return flightDAO.save(flight);
            } else {
                // ошибка
                return null;
            }
        });
    }

    private Flight setFlight(Flight flightBeta) {
        Flight flight = new Flight();
        if (flightBeta.getId() != 0) {
            flight.setId(flightBeta.getId());
        }
        flight.setNumFlight(flightBeta.getNumFlight());
        if (airportDAO.findById(flightBeta.getAirportDeparture().getId()).isPresent()) {
            flight.setAirportDeparture(airportDAO.findById(flightBeta.getAirportDeparture().getId()).get());
        } else {
            // ошибка
            return null;
        }
        if (airportDAO.findById(flightBeta.getAirportArrival().getId()).isPresent()) {
            flight.setAirportArrival(airportDAO.findById(flightBeta.getAirportArrival().getId()).get());
        } else {
            // ошибка
            return null;
        }
        flight.setDepartureDate(flightBeta.getDepartureDate());
        flight.setDepartureTime(flightBeta.getDepartureTime());
        if (aircraftDAO.findById(flightBeta.getAircraft().getId()).isPresent()) {
            flight.setAircraft(aircraftDAO.findById(flightBeta.getAircraft().getId()).get());
        } else {
            // ошибка
            return null;
        }
        flight.setPrice(flightBeta.getPrice());
        if (flightBeta.getAccountUsers() != null) {
            flight.setAccountUsers(flightBeta.getAccountUsers());
        } else {
            flight.setAccountUsers(new HashSet<UserFlight>());
        }
        return flight;

    }

    @DeleteMapping("/{Id}")
    public void deleteFlight(@PathVariable("Id") int id) {
        flightDAO.deleteById(id);
    }


}
