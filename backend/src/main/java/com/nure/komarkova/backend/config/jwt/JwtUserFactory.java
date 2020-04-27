package com.nure.komarkova.backend.config.jwt;

import com.nure.komarkova.backend.entity.Role;
import com.nure.komarkova.backend.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(user.getId(),
                user.getEmail(),
                user.getPassword(),
                true,
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles())));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
         return roles.stream()
                 .map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
