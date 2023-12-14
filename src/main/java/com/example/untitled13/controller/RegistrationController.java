package com.example.untitled13.controller;

import com.example.untitled13.entity.JwtAuthenticationModel;
import com.example.untitled13.entity.Role;
import com.example.untitled13.entity.User;
import com.example.untitled13.service.JWTUtils;
import com.example.untitled13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/sign")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtils jwtUtils;

    @PostMapping("/Up")
    public String signUp(@RequestBody JwtAuthenticationModel model){
        User user = new User();
        user.setUsername(model.getUsername());
        user.setPassword(model.getPassword());
        user = userService.saveUser(user);
        return jwtUtils.generateToken(user.getUsername(), user.getPassword(), user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
    }

}
