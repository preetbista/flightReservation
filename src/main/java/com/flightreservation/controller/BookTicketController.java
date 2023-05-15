package com.flightreservation.controller;

import com.flightreservation.model.BookTicket;
import com.flightreservation.service.BookTicketService;
import com.flightreservation.status.SeatStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book-ticket")
public class BookTicketController {
    private final BookTicketService bookTicketService;

    public BookTicketController(BookTicketService bookTicketService) {
        this.bookTicketService = bookTicketService;
    }

    @PostMapping
    public ResponseEntity<BookTicket> addBookTicket(@RequestBody BookTicket bookTicket) {
        bookTicket.setBookStatus(SeatStatus.BOOKED);
        BookTicket createdBookTicket = bookTicketService.addBookTicket(bookTicket);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBookTicket);
    }
}
