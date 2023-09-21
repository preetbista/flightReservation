package com.flightreservation.controller;

import com.flightreservation.resource.requestdto.BookTicketRequestDTO;
import com.flightreservation.resource.responsedto.BookTicketResponseDTO;
import com.flightreservation.service.BookTicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-ticket")
public class BookTicketController {
    private final BookTicketService bookTicketService;

    public BookTicketController(BookTicketService bookTicketService) {
        this.bookTicketService = bookTicketService;
    }

    @PostMapping
    public ResponseEntity<BookTicketResponseDTO> addBookTicket(@RequestBody BookTicketRequestDTO bookTicketRequestDTO) {
        BookTicketResponseDTO createdBookTicket = bookTicketService.addBookTicket(bookTicketRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBookTicket);
    }

    @GetMapping
    public List<BookTicketResponseDTO> getAll() {
        return bookTicketService.getAllUsers();
    }

    @GetMapping("/ticket")
    public List<BookTicketResponseDTO> getForRest() {
//        System.out.println("request fore getting ticket info");
        return bookTicketService.getInfoForRestTemp();
    }

}
