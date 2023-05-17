package com.flightreservation.resource.responsedto;

import com.flightreservation.model.BookTicket;
import com.flightreservation.model.Cabin;
import com.flightreservation.model.User;
import com.flightreservation.status.BookStatus;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookTicketResponseDTO implements Serializable {

    private LocalDateTime travelDate;

    private String departureAirport;

    private String arrivalAirport;

    private String departureTime;

    private String arrivalTime;

    private BookStatus bookStatus;

    public BookTicketResponseDTO(LocalDateTime travelDate, String departureAirport, String arrivalAirport, String departureTime, String arrivalTime) {
        this.travelDate = travelDate;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public static BookTicketResponseDTO of(BookTicket bookTicket) {
        return new BookTicketResponseDTO(bookTicket.getTravelDate(),
                bookTicket.getDepartureAirport(),
                bookTicket.getArrivalAirport(),
                bookTicket.getDepartureTime(),
                bookTicket.getArrivalTime(),
                bookTicket.getBookStatus()
        );
    }

    public static BookTicketResponseDTO forRest(BookTicket bookTicket) {
        return new BookTicketResponseDTO(
                bookTicket.getTravelDate(),
                bookTicket.getDepartureAirport(),
                bookTicket.getArrivalAirport(),
                bookTicket.getDepartureTime(),
                bookTicket.getArrivalTime()
        );
    }

}
