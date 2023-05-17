package com.flightreservation.resource.responsedto;

import com.flightreservation.model.User;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO implements Serializable {

    private String userName;

    private String email;

    private Long age;

    public UserResponseDTO(String userName) {
        this.userName = userName;
    }

    public static UserResponseDTO of(User user) {
        return new UserResponseDTO(user.getUserName(),
                user.getEmail(),
                user.getAge());
    }

    public static UserResponseDTO forRest(User user) {
        return new UserResponseDTO(user.getUserName()
        );
    }
}
