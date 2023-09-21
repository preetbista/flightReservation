package com.flightreservation.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.flightreservation.status.BookStatus;
import com.flightreservation.timestamp.TimeStamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@EqualsAndHashCode(callSuper = true)
public class BookTicket extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @CreationTimestamp
    private LocalDateTime travelDate;

    @Column(name = "departure_airport")
    private String departureAirport;

    @Column(name = "arrival_airport")
    private String arrivalAirport;

    @JsonIgnore
    @Column(name = "departure_time")
    private String departureTime;

    @JsonIgnore
    @Column(name = "arrival_time")
    private String arrivalTime;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cabin_id")
    private Cabin cabin;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
