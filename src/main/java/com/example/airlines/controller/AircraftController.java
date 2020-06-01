package com.example.airlines.controller;

import com.example.airlines.dao.AircraftDAO;
import com.example.airlines.dto.AircraftDTO;
import com.example.airlines.exceptions.CreateException;
import com.example.airlines.exceptions.ExceptionWhenWorkingWithDB;
import com.example.airlines.exceptions.IdSearchException;
import com.example.airlines.model.Aircraft;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aircraft")
@PreAuthorize("hasRole('ADMIN')")
public class AircraftController {

    private AircraftDAO aircraftDAO;

    public AircraftController(AircraftDAO aircraftDAO) {
        this.aircraftDAO = aircraftDAO;
    }

    @GetMapping
    public List<AircraftDTO> findAll() {
        AircraftDTO aircraftDTO = new AircraftDTO();
        return aircraftDTO.aircraftListInAircraftDTOList(aircraftDAO.findAll());
    }

    @GetMapping("{Id}")
    public AircraftDTO findByIdAircraft(@PathVariable("Id") int id) {
        AircraftDTO aircraftDTO = new AircraftDTO();
        if(aircraftDAO.findById(id).isPresent()) {
            return aircraftDTO.aircraftInAircraftDTO(aircraftDAO.findById(id).get());
        } else {
            throw new IdSearchException("Not found aircraft");
        }
    }

    /**
     * @param aircraft {
     *                 "nameAircraft": "name_aircraft",
     *                 "numberSeatsAircraft": 250
     *                 }
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public AircraftDTO saveAircraft(@RequestBody Aircraft aircraft) {
        try {
            AircraftDTO aircraftDTO = new AircraftDTO();
            return aircraftDTO.aircraftInAircraftDTO(aircraftDAO.save(aircraft));
        }catch (Exception e){
            throw new CreateException("Error when create new aircraft");
        }
    }

    /**
     * @param aircraft {
     *                 "nameAircraft": "name_aircraft",
     *                 "numberSeatsAircraft": 250
     *                 }
     */
    @PutMapping("/{Id}")
    public void updateAircraft(@PathVariable("Id") int id, @RequestBody Aircraft aircraft) {
        try {
            if( aircraftDAO.findById(id).isPresent()) {
                aircraftDAO.findById(id).map(aircraft1 -> {
                    aircraft1.setNameAircraft(aircraft.getNameAircraft());
                    aircraft1.setNumberSeatsAircraft(aircraft1.getNumberSeatsAircraft());
                    return aircraftDAO.save(aircraft1);
                });
            } else {
                throw new IdSearchException("Not found aircraft");
            }
        } catch (Exception e){
            throw new ExceptionWhenWorkingWithDB("Error when update");
        }
    }

    @DeleteMapping("/{Id}")
    public void deleteAircraft(@PathVariable("Id") int id) {
        try {
            aircraftDAO.deleteById(id);
        } catch (Exception e){
            throw new ExceptionWhenWorkingWithDB("Error when update");
        }
    }
}
