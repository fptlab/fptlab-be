package com.alda.fptlab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String Greeting() {
        return "Welcome to FptLab Application powered by Alda S.R.L.";
    }

}
