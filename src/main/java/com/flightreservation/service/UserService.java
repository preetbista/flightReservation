package com.flightreservation.service;

import com.flightreservation.resource.UserUpdateDto;
import com.flightreservation.model.User;
import com.flightreservation.resource.responsedto.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers();

    User findById(Long id);

    User findByUsername(String userName);

    User addUser(User user);

    User updateUser(UserUpdateDto userUpdateDto);

    String deleteUser(Long id);
}
