package com.flightreservation.repository;

import com.flightreservation.model.BookTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTicketRepository extends JpaRepository<BookTicket, Long> {
}
