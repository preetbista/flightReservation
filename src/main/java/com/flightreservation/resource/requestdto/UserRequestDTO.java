package com.flightreservation.resource.requestdto;

import com.flightreservation.model.BookTicket;
import com.flightreservation.model.Role;
import com.flightreservation.status.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Username cannot be blank")
    private String userName;

    @NotBlank(message = "Please create the password")
    private String password;

    @NotBlank(message = "Mention your email")
    private String email;

    @NotNull(message = "Age cannot be null")
    private Long age;

    private UserStatus status;

    private List<Role> roles;

    private List<BookTicket> bookTickets;
}
