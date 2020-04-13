package com.nure.komarkova.backend.service;

import com.nure.komarkova.backend.entity.Role;
import com.nure.komarkova.backend.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    Role findClientRole();

    List<User> findAllUsers();

    User findUserByEmail(String email);
}
