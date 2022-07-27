package com.alda.fptlab.controller;

import com.alda.fptlab.dto.request.UserDTO;
import com.alda.fptlab.dto.response.ApiResponseDTO;
import com.alda.fptlab.exception.UserAlreadyExistException;
import com.alda.fptlab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDTO> signupUser(@Valid @RequestBody UserDTO userDTO) throws UserAlreadyExistException {
        userService.signupUser(userDTO);

        ApiResponseDTO apiResponseDTO = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_OK)
                .message("Utente registrato con successo!")
                .build();

        return ResponseEntity.ok().body(apiResponseDTO);
    }

}
