package com.flightreservation.service.impl;

import com.flightreservation.exception.RoleNotFoundException;
import com.flightreservation.model.Role;
import com.flightreservation.repository.RoleRepository;
import com.flightreservation.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        return optionalRole.orElseThrow(() -> new RoleNotFoundException("Role not found for id :"+id));
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        findById(role.getId());
        return roleRepository.save(role);
    }

    @Override
    public String deleteRole(Long id) {
        findById(id);
        roleRepository.deleteById(id);
        return "Role deleted successfully";
    }
}
