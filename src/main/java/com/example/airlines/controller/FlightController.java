package com.example.airlines.controller;

import com.example.airlines.dao.AircraftDAO;
import com.example.airlines.dao.AirportDAO;
import com.example.airlines.dao.FlightDAO;
import com.example.airlines.dao.UserFlightDAO;
import com.example.airlines.dto.FlightRoleUserDTO;
import com.example.airlines.exceptions.CreateException;
import com.example.airlines.exceptions.ExceptionWhenWorkingWithDB;
import com.example.airlines.exceptions.IdSearchException;
import com.example.airlines.model.Flight;
import com.example.airlines.model.UserFlight;
import com.example.airlines.parser.FlightParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
            throw new IdSearchException("Did not found flight");
        } else {
            return flightRoleUserDTO.flightToFlightClientDTO(flightDAO.findById(id).get());
        }
    }

    /**
     * Фильтрация по подстроке
     */
    @GetMapping("/findFlight/{str}")
    public List<FlightRoleUserDTO> findFlight(@PathVariable("str") String str) {
        FlightRoleUserDTO flightsDTO = new FlightRoleUserDTO();
        return flightsDTO.flightListToFlightClientDTOList(flightDAO.findFlights("%" + str + "%"));
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
     * @param {
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
    public FlightRoleUserDTO saveFlight(@RequestBody String flightString) {
            FlightRoleUserDTO flightsDTO = new FlightRoleUserDTO();
            try {
                Flight flightBeta = FlightParser.flightParser(flightString);
                Flight flight = setFlight(flightBeta);
                if (flight != null) {
                    try {
                        return flightsDTO.flightToFlightClientDTO(flightDAO.save(flight));
                    } catch (Exception e) {
                        throw new ExceptionWhenWorkingWithDB("Did not create airport");
                    }
                } else {
                    throw new IdSearchException("Did not found flight");
                }
            } catch (Exception ex){
                throw new CreateException("Did not create flight");
            }
        }

        /**
         * @param id         id рейса который хотите изменить
         * @param {
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
        public void updateFlight ( @PathVariable("Id") int id, @RequestBody String flightString){
            try {
                Flight flightBeta = FlightParser.flightParser(flightString);
                if (flightDAO.findById(id).isPresent()) {
                    flightDAO.findById(id).map(flight -> {
                        flightBeta.setId(id);
                        flight = setFlight(flightBeta);
                        if (flight != null) {
                            try {
                                return flightDAO.save(flight);
                            } catch (Exception e) {
                                throw new ExceptionWhenWorkingWithDB("Did not create airport");
                            }
                        } else {
                            throw new IdSearchException("Did not found flight");
                        }
                    });
                } else {
                    throw new IdSearchException("Did not found flight");
                }
            } catch (Exception e){
                throw new CreateException("Did not create flight");
            }
        }

        private Flight setFlight (Flight flightBeta){
            Flight flight = new Flight();
            if (flightBeta.getId() != 0) {
                flight.setId(flightBeta.getId());
            }
            flight.setNumFlight(flightBeta.getNumFlight());
            if (airportDAO.findById(flightBeta.getAirportDeparture().getId()).isPresent()) {
                flight.setAirportDeparture(airportDAO.findById(flightBeta.getAirportDeparture().getId()).get());
            } else {
                return null;
            }
            if (airportDAO.findById(flightBeta.getAirportArrival().getId()).isPresent()) {
                flight.setAirportArrival(airportDAO.findById(flightBeta.getAirportArrival().getId()).get());
            } else {
                return null;
            }
            flight.setDepartureDate(flightBeta.getDepartureDate());
            flight.setDepartureTime(flightBeta.getDepartureTime());
            if (aircraftDAO.findById(flightBeta.getAircraft().getId()).isPresent()) {
                flight.setAircraft(aircraftDAO.findById(flightBeta.getAircraft().getId()).get());
            } else {
                return null;
            }
            flight.setPrice(flightBeta.getPrice());
            if (flightBeta.getAccountUsers() != null) {
                flight.setAccountUsers(flightBeta.getAccountUsers());
            } else {
                flight.setAccountUsers(new HashSet<UserFlight>());
            }
            if(flight.getAirportDeparture().getId() == flight.getAirportArrival().getId()){
                return null;
            }
            return flight;
        }


        @DeleteMapping("/{Id}")
        public void deleteFlight ( @PathVariable("Id") int id){
            try {
                flightDAO.deleteById(id);
            } catch (Exception e){
                throw new ExceptionWhenWorkingWithDB("Did not delete airport");
            }
        }
    }
