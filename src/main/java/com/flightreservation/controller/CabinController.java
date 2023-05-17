package com.flightreservation.controller;

import com.flightreservation.exception.CabinAlreadyExistException;
import com.flightreservation.resource.requestdto.CabinRequestDTO;
import com.flightreservation.resource.responsedto.CabinResponseDTO;
import com.flightreservation.service.CabinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabins")
public class CabinController {

    private final CabinService cabinService;

    public CabinController(CabinService cabinService) {
        this.cabinService = cabinService;
    }

    @PostMapping
    public ResponseEntity<List<CabinResponseDTO>> addSeat(@RequestBody CabinRequestDTO cabinRequestDTO) {
        try {
            List<CabinResponseDTO> addedCabins = cabinService.addSeat(cabinRequestDTO);
            return new ResponseEntity<>(addedCabins, HttpStatus.CREATED);
        } catch (CabinAlreadyExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<CabinResponseDTO> getAll() {
        return cabinService.getAllCabin();
    }

    @GetMapping("/cabin-rest")
    public List<String> getCabinName() {
        return cabinService.getCabinSeatName();
    }

}
