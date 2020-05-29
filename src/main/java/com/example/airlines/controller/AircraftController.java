package com.example.airlines.controller;

import com.example.airlines.dao.AircraftDAO;
import com.example.airlines.dto.AircraftDTO;
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
        return aircraftDTO.aircraftInAircraftDTO(aircraftDAO.findById(id).get());
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
        AircraftDTO aircraftDTO = new AircraftDTO();
        return aircraftDTO.aircraftInAircraftDTO(aircraftDAO.save(aircraft));
    }

    /**
     * @param aircraft {
     *                 "nameAircraft": "name_aircraft",
     *                 "numberSeatsAircraft": 250
     *                 }
     */
    @PutMapping("/{Id}")
    public void updateAircraft(@PathVariable("Id") int id, @RequestBody Aircraft aircraft) {
        aircraftDAO.findById(id).map(aircraft1 -> {
            aircraft1.setNameAircraft(aircraft.getNameAircraft());
            aircraft1.setNumberSeatsAircraft(aircraft1.getNumberSeatsAircraft());
            return aircraftDAO.save(aircraft1);
        });
    }

    @DeleteMapping("/{Id}")
    public void deleteAircraft(@PathVariable("Id") int id) {
        aircraftDAO.deleteById(id);
    }
}
