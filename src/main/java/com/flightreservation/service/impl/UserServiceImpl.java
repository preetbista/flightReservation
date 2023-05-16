package com.flightreservation.service.impl;

import com.flightreservation.resource.UserUpdateDto;
import com.flightreservation.exception.UserNotFoundException;
import com.flightreservation.model.User;
import com.flightreservation.repository.UserRepository;
import com.flightreservation.resource.requestdto.UserRequestDTO;
import com.flightreservation.resource.responsedto.UserResponseDTO;
import com.flightreservation.service.UserService;
import com.flightreservation.status.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /*private final BCryptPasswordEncoder bCryptPasswordEncoder;*/

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new UserNotFoundException("User not found for id :"+id));
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User addUser(User user) {

        /*String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);*/
        user.setStatus(UserStatus.ACTIVE); // Set the status to "active"
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserUpdateDto userUpdateDto) {
        User user = findById(userUpdateDto.getId());
        user.setUserName(userUpdateDto.getUserName());
        user.setAge(userUpdateDto.getAge());
        return userRepository.save(user);
    }

    @Override
    public String deleteUser(Long id) {
        User user = findById(id);
        if (user == null) {
            return "User not found";
        }

        user.setStatus(UserStatus.DELETED); // Update the status to "deleted"
        userRepository.save(user); // Save the updated user with the new status

        return "User deleted successfully";
    }

}
