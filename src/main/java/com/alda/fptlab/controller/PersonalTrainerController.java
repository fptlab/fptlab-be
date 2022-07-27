package com.alda.fptlab.controller;

import com.alda.fptlab.dto.request.PersonalTrainerDTO;
import com.alda.fptlab.dto.response.ApiResponseDTO;
import com.alda.fptlab.service.PersonalTrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personal-trainers")
public class PersonalTrainerController {

    private final PersonalTrainerService personalTrainerService;

    @PostMapping("")
    public ResponseEntity<ApiResponseDTO> savePersonalTrainer(@RequestBody PersonalTrainerDTO personalTrainerDTO) {
        personalTrainerService.savePersonalTrainer(personalTrainerDTO);
        ApiResponseDTO apiResponseDTO = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_OK)
                .message("Personal trainer aggiunto con successo!")
                .build();
        return ResponseEntity.ok().body(apiResponseDTO);
    }
}

