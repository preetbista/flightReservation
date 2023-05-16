package com.flightreservation.service;

import com.flightreservation.model.BookTicket;
import com.flightreservation.resource.requestdto.BookTicketRequestDTO;
import com.flightreservation.resource.responsedto.BookTicketResponseDTO;

import java.util.List;

public interface BookTicketService {
    BookTicketResponseDTO addBookTicket(BookTicketRequestDTO bookTicketRequestDTO);

    List<BookTicketResponseDTO> getAllUsers();
}
