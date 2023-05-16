package com.flightreservation.service.impl;

import com.flightreservation.exception.CabinAlreadyExistException;
import com.flightreservation.model.Cabin;
import com.flightreservation.repository.CabinRepository;
import com.flightreservation.resource.requestdto.CabinRequestDTO;
import com.flightreservation.resource.responsedto.CabinResponseDTO;
import com.flightreservation.service.CabinService;
import com.flightreservation.status.SeatStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CabinServiceImpl implements CabinService {

    private final CabinRepository cabinRepository;

    @Override
    public List<CabinResponseDTO> addSeat(CabinRequestDTO cabinRequestDTO) throws CabinAlreadyExistException {
        List<Cabin> cabinList = new ArrayList<>();
        for (int i = 1; i <= cabinRequestDTO.getMaxSeat(); i++) {
            String seatName = cabinRequestDTO.getSeatName() + i;
            if (cabinRepository.existsBySeatName(seatName)) {
                throw new CabinAlreadyExistException("Cabin information has already been placed ");
            }
            cabinList.add(new Cabin(seatName, SeatStatus.AVAILABLE));
        }

        cabinList = cabinRepository.saveAll(cabinList);

        List<CabinResponseDTO> cabinResponseDTOList = new ArrayList<>();
        for (Cabin cabin : cabinList) {
            CabinResponseDTO cabinResponseDTO = new CabinResponseDTO();
            cabinResponseDTO.setSeatName(cabin.getSeatName());
            cabinResponseDTO.setAvailability(cabin.getAvailability());

            cabinResponseDTOList.add(cabinResponseDTO);
        }
        return  cabinResponseDTOList;
    }

    @Override
    public List<CabinResponseDTO> getAllCabin(){
        return cabinRepository.findAll()
                .stream()
                .map(CabinResponseDTO::of)
                .collect(Collectors.toList());
    }
}
