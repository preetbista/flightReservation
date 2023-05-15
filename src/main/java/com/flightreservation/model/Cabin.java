package com.flightreservation.model;

import com.flightreservation.status.SeatStatus;
import com.flightreservation.timestamp.TimeStamp;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cabins")
@EqualsAndHashCode(callSuper = true)
public class Cabin extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_name")
    private String seatName;

    @Column(name = "availability")
    @Enumerated(EnumType.STRING)
    private SeatStatus availability;

    @Transient
    private int maxSeat;

    public Cabin(String seatName, SeatStatus availability) {
        this.seatName = seatName;
        this.availability = availability;
    }
}
