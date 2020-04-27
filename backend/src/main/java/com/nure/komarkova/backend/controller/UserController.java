package com.nure.komarkova.backend.controller;

import com.google.gson.Gson;
import com.nure.komarkova.backend.bean.RegisterBean;
import com.nure.komarkova.backend.entity.Role;
import com.nure.komarkova.backend.entity.User;
import com.nure.komarkova.backend.service.UserService;
import com.nure.komarkova.backend.util.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/createUser")
    public HttpStatus createUser(@RequestBody String data) {
        User user = new User();
        RegisterBean registerBean = new Gson().fromJson(data, RegisterBean.class);
        if (!isValidRegisterBean(registerBean)) {
            return HttpStatus.BAD_REQUEST;
        }
        user.setEmail(registerBean.getEmail());
        user.setPassword(new Util().encryptString(registerBean.getPassword()));
        Role role = userService.findClientRole();
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        userService.saveUser(user);
        return HttpStatus.OK;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getUser/{email}")
    public ResponseEntity<User> getUser(@PathVariable("email") String email) {
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);
    }

    private boolean isValidRegisterBean(RegisterBean registerBean) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<RegisterBean>> violations = validator.validate(registerBean);
        for (ConstraintViolation<RegisterBean> violation : violations) {
            if (violation.getInvalidValue() != null) {
                return false;
            }
        }
        return true;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/findAllUsers")
    public String findAllUsers() {
        return new Gson().toJson(userService.findAllUsers());
    }
}
