package com.nure.komarkova.backend.config;

import com.nure.komarkova.backend.config.jwt.JwtUser;
import com.nure.komarkova.backend.config.jwt.JwtUserFactory;
import com.nure.komarkova.backend.entity.User;
import com.nure.komarkova.backend.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(s);

        if (user == null) {
            throw new UsernameNotFoundException("User with email: "+ s + " Not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }
}
