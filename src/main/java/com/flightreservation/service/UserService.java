package com.flightreservation.service;

import com.flightreservation.dto.UserUpdateDto;
import com.flightreservation.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User findById(Long id);

    User findByUsername(String userName);

    User addUser(User user);

    User updateUser(UserUpdateDto userUpdateDto);

    String deleteUser(Long id);
}
