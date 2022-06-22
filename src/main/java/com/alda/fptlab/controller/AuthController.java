package com.alda.fptlab.controller;

import com.alda.fptlab.model.UserModel;
import com.alda.fptlab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signupUser(@RequestBody UserModel userModel) {
        userService.signupUser(userModel);
        return "Registration Success!";
    }
}
