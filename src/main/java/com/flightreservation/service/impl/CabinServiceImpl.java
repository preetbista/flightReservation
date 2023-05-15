package com.flightreservation.service.impl;

import com.flightreservation.exception.CabinAlreadyExistException;
import com.flightreservation.model.Cabin;
import com.flightreservation.repository.CabinRepository;
import com.flightreservation.service.CabinService;
import com.flightreservation.status.SeatStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CabinServiceImpl implements CabinService {

    private final CabinRepository cabinRepository;

    @Override
    public List<Cabin> addSeat(Cabin cabin) throws CabinAlreadyExistException {
        List<Cabin> cabinList = new ArrayList<>();
        for (int i = 1; i <= cabin.getMaxSeat(); i++) {
            String seatName = cabin.getSeatName() + i;
            if (cabinRepository.existsBySeatName(seatName)) {
                throw new CabinAlreadyExistException("Cabin information has already been stated ");
            }
            cabinList.add(new Cabin(seatName, SeatStatus.AVAILABLE));
        }
        return cabinRepository.saveAll(cabinList);
    }
}
