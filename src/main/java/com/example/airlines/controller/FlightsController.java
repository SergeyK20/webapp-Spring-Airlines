package com.example.airlines.controller;

import com.example.airlines.dao.FlightsDAO;
import com.example.airlines.dto.FlightClientDTO;
import com.example.airlines.dto.FlightsAdminDTO;
import com.example.airlines.model.Flights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightsController {
    private FlightsDAO flightsDAO;

    @Autowired
    public FlightsController(FlightsDAO flightsDAO) {
        this.flightsDAO = flightsDAO;
    }

    /**
     * @return возвращает список рейсов без аккаунтов
     */
    @GetMapping
    public List<FlightClientDTO> getAllCityClient() {
        FlightClientDTO flightsDTO = new FlightClientDTO();
        return flightsDTO.flightListToFlightClientDTOList(flightsDAO.findAll());
    }

    /**
     * @return возвращает список рейсов с аккаунтами
     */
    @GetMapping("/admin")
    public List<FlightsAdminDTO> getAllCityAdmin() {
        FlightsAdminDTO flightsAdminDTO = new FlightsAdminDTO();
        return flightsAdminDTO.flightListToFlightsDTOList(flightsDAO.findAll());
    }

    /**
     * @return возвращает список рейс без аккаунта
     */
    @GetMapping("/{Id}")
    public FlightClientDTO getByIdFlightClientDTO(@PathVariable("Id") int id) {
        FlightClientDTO flightClientDTO = new FlightClientDTO();
        if (!flightsDAO.findById(id).isPresent()) {
            return null;
        } else {
            return flightClientDTO.flightToFlightClientDTO(flightsDAO.findById(id).get());
        }
    }

    /**
     * @return возвращает список рейс с аккаунтом
     */
    @GetMapping("/admin/{Id}")
    public FlightsAdminDTO getByIdFlight(@PathVariable("Id") int id) {
        FlightsAdminDTO flightsAdminDTO = new FlightsAdminDTO();
        if (!flightsDAO.findById(id).isPresent()) {
            return null;
        } else {
            return flightsAdminDTO.flightToFlightsDTO(flightsDAO.findById(id).get());
        }
    }

    /**
     * @param flight в теле должны обязательно присутствовать номер рейса, id аэропорт откуда вылетает,
     *               id аэропорта куда прилетает, время, дата и название рейса
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Flights saveFlight(@RequestBody Flights flight) {
        return flightsDAO.save(flight);
    }

    /**
     * @param id     id рейса который хотите изменить
     * @param flight в теле должны обязательно присутствовать номер рейса, id аэропорт откуда вылетает, id города, название города,
     *               id аэропорта куда прилетает, id города, название города, время, дата и название рейса
     */
    @PutMapping("/{Id}")
    public void updateFlight(@PathVariable("Id") int id, @RequestBody Flights flight) {
        flightsDAO.findById(id).map(flights -> {
            flights.setIdFlight(id);
            flights.setNumFlight(flight.getNumFlight());
            flights.setAirportsDeparture(flight.getAirportsDeparture());
            flights.setAirportsArrival(flight.getAirportsArrival());
            flights.setDepartureDate(flight.getDepartureDate());
            flights.setDepartureTime(flight.getDepartureTime());
            flights.setAircraftName(flight.getAircraftName());
            return flightsDAO.save(flights);
        });
    }

    @DeleteMapping("/{Id}")
    public void deleteFlight(@PathVariable("Id") int id) {
        flightsDAO.deleteById(id);
    }


}
