package com.example.airlines.controller;

import com.example.airlines.dao.FlightsDAO;
import com.example.airlines.dto.FlightClientDTO;
import com.example.airlines.dto.FlightsAdminDTO;
import com.example.airlines.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PreAuthorize("hasAuthority('USER')")
    public List<FlightClientDTO> getAllCityClient() {
        FlightClientDTO flightsDTO = new FlightClientDTO();
        return flightsDTO.flightListToFlightClientDTOList(flightsDAO.findAll());
    }

    /**
     * @return возвращает список рейсов с аккаунтами
     */
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<FlightsAdminDTO> getAllCityAdmin() {
        FlightsAdminDTO flightsAdminDTO = new FlightsAdminDTO();
        return flightsAdminDTO.flightListToFlightsDTOList(flightsDAO.findAll());
    }

    /**
     * @return возвращает список рейс без аккаунта
     */
    @GetMapping("/{Id}")
    @PreAuthorize("hasAuthority('USER')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public Flight saveFlight(@Valid @RequestBody Flight flight) {
        return flightsDAO.save(flight);
    }

    /**
     * @param id     id рейса который хотите изменить
     * @param flight в теле должны обязательно присутствовать номер рейса, id аэропорт откуда вылетает, id города, название города,
     *               id аэропорта куда прилетает, id города, название города, время, дата и название рейса
     */
    @PutMapping("/{Id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateFlight(@PathVariable("Id") int id, @RequestBody Flight flight) {
        flightsDAO.findById(id).map(flights -> {
            flights.setIdFlight(id);
            flights.setNumFlight(flight.getNumFlight());
            flights.setAirportDeparture(flight.getAirportDeparture());
            flights.setAirportArrival(flight.getAirportArrival());
            flights.setDepartureDate(flight.getDepartureDate());
            flights.setDepartureTime(flight.getDepartureTime());
            flights.setAircraftName(flight.getAircraftName());
            return flightsDAO.save(flights);
        });
    }

    @DeleteMapping("/{Id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteFlight(@PathVariable("Id") int id) {
        flightsDAO.deleteById(id);
    }


}
