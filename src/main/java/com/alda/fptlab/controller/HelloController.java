package com.alda.fptlab.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> Greeting() {
        return ResponseEntity.ok().body("Welcome to FptLab Application powered by Alda S.R.L.");
    }

}
