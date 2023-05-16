package com.flightreservation.resource.responsedto;

import com.flightreservation.model.Cabin;
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
public class CabinResponseDTO implements Serializable {

    private String seatName;

    private SeatStatus availability;

    public static CabinResponseDTO of(Cabin cabin){
        return new CabinResponseDTO(cabin.getSeatName(),
                cabin.getAvailability());
    }


}
