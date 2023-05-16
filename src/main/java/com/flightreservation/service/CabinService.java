package com.flightreservation.service;

import com.flightreservation.exception.CabinAlreadyExistException;
import com.flightreservation.model.Cabin;
import com.flightreservation.resource.requestdto.CabinRequestDTO;
import com.flightreservation.resource.responsedto.CabinResponseDTO;

import java.util.List;

public interface CabinService {
    List<CabinResponseDTO> addSeat(CabinRequestDTO cabinRequestDTO) throws CabinAlreadyExistException ;

    List<CabinResponseDTO> getAllCabin();
}
