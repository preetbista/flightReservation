package com.flightreservation.resource.requestdto;

import com.flightreservation.model.BookTicket;
import com.flightreservation.model.Cabin;
import com.flightreservation.model.User;
import com.flightreservation.status.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookTicketRequestDTO implements Serializable {

    private LocalDateTime travelDate;

    private String departureAirport;

    private String arrivalAirport;

    private String departureTime;

    private String arrivalTime;

    private BookStatus bookStatus;

    private Cabin cabin;

    private User user;

}
