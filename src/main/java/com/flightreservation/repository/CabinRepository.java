package com.flightreservation.repository;

import com.flightreservation.model.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabinRepository extends JpaRepository<Cabin, Long> {
    boolean existsBySeatName(String seatName);
}
