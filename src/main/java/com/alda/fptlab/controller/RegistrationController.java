package com.alda.fptlab.controller;

import com.alda.fptlab.dto.UserDTO;
import com.alda.fptlab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/signup")
    public String saveUser(@Valid @RequestBody UserDTO userModel) {
        userService.saveUser(userModel);
        return "Registration Success!";
    }

}
