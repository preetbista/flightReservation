package com.flightreservation.resource.responsedto;

import com.flightreservation.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO implements Serializable {

    private String userName;

    private String email;

    private Long age;

    public static UserResponseDTO of(User user){
        return new UserResponseDTO(user.getUserName(),
                user.getEmail(),
                user.getAge());
    }
}
