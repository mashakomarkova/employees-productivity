package com.nure.komarkova.backend.service.impl;

import com.nure.komarkova.backend.entity.Role;
import com.nure.komarkova.backend.entity.User;
import com.nure.komarkova.backend.repository.RoleRepository;
import com.nure.komarkova.backend.repository.UserRepository;
import com.nure.komarkova.backend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Role findClientRole() {
        return roleRepository.findByName("client");
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
