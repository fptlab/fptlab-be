package com.alda.fptlab.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> Greeting() {
        return ResponseEntity.ok().body("Welcome to FptLab Application powered by Alda S.R.L.");
    }

}

