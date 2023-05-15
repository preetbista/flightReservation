package com.flightreservation.service;

import com.flightreservation.exception.CabinAlreadyExistException;
import com.flightreservation.model.Cabin;

import java.util.List;

public interface CabinService {
    List<Cabin> addSeat(Cabin cabin) throws CabinAlreadyExistException;
}
