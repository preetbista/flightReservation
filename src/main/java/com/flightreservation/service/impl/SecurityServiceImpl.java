/*
package com.flightreservation.service.impl;

import com.flightreservation.model.User;
import com.flightreservation.service.SecurityService;
import com.flightreservation.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    private final UserService userService;



    public SecurityServiceImpl(UserService userService, UserService userService1) {
        this.userService = userService1;
    }


    @Override
    public boolean isAuthorized(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String authenticatedUserName = authentication.getName();
            User authenticatedUser = userService.findByUsername(authenticatedUserName);
            return authenticatedUser != null && authenticatedUser.getId().equals(userId);
        }
        return false;
    }

    
}
*/
