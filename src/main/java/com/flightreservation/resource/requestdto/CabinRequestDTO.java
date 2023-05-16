package com.flightreservation.resource.requestdto;

import com.flightreservation.status.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CabinRequestDTO implements Serializable {

    private Long id;

    private String seatName;

    private SeatStatus availability;

    private int maxSeat;
}
