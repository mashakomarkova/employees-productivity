package com.nure.komarkova.backend.service.impl;

import com.nure.komarkova.backend.entity.Role;
import com.nure.komarkova.backend.repository.RoleRepository;
import com.nure.komarkova.backend.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
