package com.flightreservation.service;

import com.flightreservation.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role findById(Long id);
    Role addRole(Role role);
    Role updateRole(Role role);
    String deleteRole(Long id);
}
