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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        log.info("Getting all the user information");
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    public User findById(Long id) {
        log.info("finding user by id");
        Optional<User> optionalUser = userRepository.findById(id);
        log.info("Returning user by id");
        return optionalUser.orElseThrow(() -> new UserNotFoundException("User not found for id :" + id));
    }

    @Override
    public User findByUsername(String userName) {
        log.info("finding user by username");
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        log.info("adding user");
        String encodedPassword = bCryptPasswordEncoder.encode(userRequestDTO.getPassword());
        userRequestDTO.setPassword(encodedPassword);
        User user = new User();
        user.setUserName(userRequestDTO.getUserName());
        user.setEmail(userRequestDTO.getEmail());
        user.setAge(userRequestDTO.getAge());
        user.setPassword(userRequestDTO.getPassword());
        user.setStatus(UserStatus.ACTIVE);
        user.setRoles(userRequestDTO.getRoles());

        /*List<Role> roles = new ArrayList<>();
          roles.add(new Role(null,"USER"));
          user.setRoles(roles);*/

        user = userRepository.save(user);
        log.info("service | user added successfully ");
        return UserResponseDTO.builder()
                .userName(user.getUserName())
                .age(user.getAge())
                .email(user.getEmail())
                .build();
    }

    @Override
    public User updateUser(UserUpdateDto userUpdateDto) {
        log.info("updating user");
        User user = findById(userUpdateDto.getId());
        user.setUserName(userUpdateDto.getUserName());
        user.setAge(userUpdateDto.getAge());
        log.info("user updated");
        return userRepository.save(user);
    }

    @Override
    public String deleteUser(Long id) {
        log.info("deleting user");
        User user = findById(id);
        if (user == null) {
            return "User not found";
        }

        user.setStatus(UserStatus.DELETED); // Update the status to "deleted"
        userRepository.save(user); // Save the updated user with the new status
        log.info("user deleted successfully");
        return "User deleted successfully";
    }

    @Override
    public List<String> getUsernameForRest() {
        log.info("inside getUserNameForRest");
        List<UserResponseDTO> userResponseDTOList =  userRepository.findAll()
                .stream()
                .map(UserResponseDTO::forRest)
                .collect(Collectors.toList());
        List<String> userNameList = new ArrayList<>();
        for (UserResponseDTO userResponseDTO : userResponseDTOList) {
            userNameList.add(userResponseDTO.getUserName());
        }
        log.info("List of username returning: "+userNameList);
        return userNameList;

    }

}
