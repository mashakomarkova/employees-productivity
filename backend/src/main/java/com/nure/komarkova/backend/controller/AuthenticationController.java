package com.nure.komarkova.backend.controller;

import com.google.gson.Gson;
import com.nure.komarkova.backend.config.jwt.JwtTokenProvider;
import com.nure.komarkova.backend.dto.AuthenticationRequestDto;
import com.nure.komarkova.backend.entity.User;
import com.nure.komarkova.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    public AuthenticationController(UserService userService, AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @CrossOrigin("*")
    @PostMapping("/logIn")
    public ResponseEntity signIn(@RequestBody String data) {
        try {
            AuthenticationRequestDto requestDto = new Gson().fromJson(data, AuthenticationRequestDto.class);
            String username = requestDto.getUsername();
           // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findUserByEmail(username);
            if (user == null) {
                throw new UsernameNotFoundException("not found username");
            }
            String token = jwtTokenProvider.createToken(username, user.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        }
        catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
