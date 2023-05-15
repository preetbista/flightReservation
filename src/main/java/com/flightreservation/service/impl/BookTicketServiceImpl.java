package com.flightreservation.service.impl;

import com.flightreservation.model.BookTicket;
import com.flightreservation.repository.BookTicketRepository;
import com.flightreservation.service.BookTicketService;
import com.flightreservation.status.SeatStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookTicketServiceImpl implements BookTicketService {

    private final BookTicketRepository bookTicketRepository;

    @Override
    public BookTicket addBookTicket(BookTicket bookTicket){
        bookTicket.setBookStatus(SeatStatus.BOOKED);
        return bookTicketRepository.save(bookTicket);
    }

}
