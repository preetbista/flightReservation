package com.flightreservation.controller;

import com.flightreservation.resource.UserUpdateDto;
import com.flightreservation.model.User;
import com.flightreservation.resource.requestdto.UserRequestDTO;
import com.flightreservation.resource.responsedto.UserResponseDTO;
import com.flightreservation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<UserResponseDTO> getAll() {
        log.info("controller | getting all users");
        return userService.getAllUsers();
    }

    @GetMapping("/find/{id}")
    /*@PreAuthorize("hasAuthority('USER') and @securityService.isAuthorized(#id)")*/
    public User findById(@PathVariable Long id) {
        log.info("Controller | finding user by id");
        return userService.findById(id);
    }

    @GetMapping("/username/{username}")
    public User findByUsername(@PathVariable String username) {
        log.info("controller | finding by username");
        return userService.findByUsername(username);
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        log.info("Controller | Adding user");
        UserResponseDTO createdUser = userService.addUser(userRequestDTO);
        log.info("controller | user added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody UserUpdateDto user) {
        log.info("Controller | update user");
        User updatedUser = userService.updateUser(user);
        log.info("user updated successfully");
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        log.info("controller | deleting user");
        String result = userService.deleteUser(id);
        if (result.equals("User not found")) {
            log.info("failed to delete user");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            log.info("controller | deleted user ");
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @GetMapping("/user-rest")
    public List<String> getUsernameRest() {
        log.info("controller | getting user data for rest template");
        return userService.getUsernameForRest();
    }
}


