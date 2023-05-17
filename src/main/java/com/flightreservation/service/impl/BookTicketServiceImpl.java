package com.flightreservation.service.impl;

import com.flightreservation.exception.SeatNotAvailableException;
import com.flightreservation.model.BookTicket;
import com.flightreservation.model.Cabin;
import com.flightreservation.model.User;
import com.flightreservation.repository.BookTicketRepository;
import com.flightreservation.repository.CabinRepository;
import com.flightreservation.resource.requestdto.BookTicketRequestDTO;
import com.flightreservation.resource.responsedto.BookTicketResponseDTO;
import com.flightreservation.service.BookTicketService;
import com.flightreservation.service.UserService;
import com.flightreservation.status.BookStatus;
import com.flightreservation.status.SeatStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookTicketServiceImpl implements BookTicketService {

    private final BookTicketRepository bookTicketRepository;

    private final CabinRepository cabinRepository;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public BookTicketResponseDTO addBookTicket(BookTicketRequestDTO bookTicketRequestDTO) {
        String seatName = bookTicketRequestDTO.getCabin().getSeatName();
        Cabin cabin = cabinRepository.findBySeatName(seatName);
        if (cabin != null && cabin.getAvailability().equals(SeatStatus.AVAILABLE)) {
            cabin.setAvailability(SeatStatus.BOOKED);
            cabinRepository.save(cabin);

            BookTicket bookTicket = new BookTicket();
            bookTicket.setCabin(cabin);
            bookTicket.setTravelDate(bookTicketRequestDTO.getTravelDate());
            bookTicket.setDepartureTime(bookTicketRequestDTO.getDepartureTime());
            bookTicket.setArrivalTime(bookTicketRequestDTO.getArrivalTime());
            bookTicket.setDepartureAirport(bookTicketRequestDTO.getDepartureAirport());
            bookTicket.setArrivalAirport(bookTicketRequestDTO.getArrivalAirport());
            bookTicket.setBookStatus(BookStatus.BOOKED);
            User user = userService.findById(bookTicketRequestDTO.getUser().getId());
            bookTicket.setUser(user);

            bookTicket = bookTicketRepository.save(bookTicket);

            return BookTicketResponseDTO.builder()
                    .arrivalAirport(bookTicket.getArrivalAirport())
                    .departureAirport(bookTicket.getDepartureAirport())
                    .travelDate(bookTicket.getTravelDate())
                    .departureTime(bookTicket.getDepartureTime())
                    .arrivalTime(bookTicket.getArrivalTime())
                    .bookStatus(bookTicket.getBookStatus())
                    .build();
        } else {
            throw new SeatNotAvailableException("Seat is not available for booking.");
        }
    }

    @Override
    public List<BookTicketResponseDTO> getAllUsers() {
        return bookTicketRepository.findAll().stream()
                .map(BookTicketResponseDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookTicketResponseDTO> getInfoForRestTemp() {
        return bookTicketRepository.findAll().stream()
                .map(BookTicketResponseDTO::forRest)
                .collect(Collectors.toList());
    }
}
