package com.alda.fptlab.controller;

import com.alda.fptlab.model.UserModel;
import com.alda.fptlab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/signup")
    public String saveUser(@RequestBody UserModel userModel) {
        userService.saveUser(userModel);
        return "Registration Success!";
    }

}
