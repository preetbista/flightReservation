package com.flightreservation.controller;

import com.flightreservation.exception.UserNotFoundException;
import com.flightreservation.exception.UsernameAndPasswordNotMatchException;
import com.flightreservation.model.User;
import com.flightreservation.resource.ResponseTokenMessage;
import com.flightreservation.resource.requestdto.UserRequestDTO;
import com.flightreservation.service.UserService;
import com.flightreservation.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping
    public ResponseEntity<ResponseTokenMessage> login(@RequestBody UserRequestDTO userRequestDTO){
        Optional<User> optionalUser = Optional.ofNullable(userService.findByUsername(userRequestDTO.getUserName()));
        User user = optionalUser.orElseThrow(() -> new UserNotFoundException("No user found with name : "+ userRequestDTO.getUserName()));
        if(bCryptPasswordEncoder.matches(userRequestDTO.getPassword(), user.getPassword())){
            ResponseTokenMessage response  = new ResponseTokenMessage(JwtUtil.getJWTToken(user), user.getUserName());
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        throw new UsernameAndPasswordNotMatchException("Username or password do not match");
    }
}
