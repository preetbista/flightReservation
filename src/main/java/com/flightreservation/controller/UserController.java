package com.flightreservation.controller;

import com.flightreservation.resource.UserUpdateDto;
import com.flightreservation.model.User;
import com.flightreservation.resource.requestdto.UserRequestDTO;
import com.flightreservation.resource.responsedto.UserResponseDTO;
import com.flightreservation.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDTO> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/find/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/username/{username}")
    public User findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userService.addUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody UserUpdateDto user) {
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String result = userService.deleteUser(id);
        if (result.equals("User not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @GetMapping("/user-rest")
    public List<String> getUsernameRest() {
        return userService.getUsernameForRest();
    }
}
