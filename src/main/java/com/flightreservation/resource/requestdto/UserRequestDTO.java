package com.flightreservation.resource.requestdto;

import com.flightreservation.model.BookTicket;
import com.flightreservation.model.Role;
import com.flightreservation.status.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO implements Serializable {

    private Long id;

    private String userName;

    private String password;

    private String email;

    private Long age;

    private UserStatus status;

    private List<Role> roles;

    private List<BookTicket> bookTickets;
}
