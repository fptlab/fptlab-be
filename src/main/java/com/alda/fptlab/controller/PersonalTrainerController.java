package com.alda.fptlab.controller;

import com.alda.fptlab.dto.request.PersonalTrainerDTO;
import com.alda.fptlab.dto.response.ApiResponseDTO;
import com.alda.fptlab.exception.PersonalTrainerNotFoundException;
import com.alda.fptlab.service.PersonalTrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/personal-trainers")
public class PersonalTrainerController {

    private final PersonalTrainerService personalTrainerService;

    @GetMapping("")
    public ResponseEntity<ApiResponseDTO> fetchPersonalTrainerList() {
        ApiResponseDTO apiResponseDTO = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_OK)
                .result(personalTrainerService.fetchPersonalTrainerList())
                .build();
        return ResponseEntity.ok().body(apiResponseDTO);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponseDTO> savePersonalTrainer(@RequestBody PersonalTrainerDTO personalTrainerDTO) {
        personalTrainerService.savePersonalTrainer(personalTrainerDTO);
        ApiResponseDTO apiResponseDTO = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_OK)
                .message("Personal trainer aggiunto con successo!")
                .build();
        return ResponseEntity.ok().body(apiResponseDTO);
    }

    @PutMapping("/{personalTrainerId}")
    public ResponseEntity<ApiResponseDTO> updatePersonalTrainer(@PathVariable("personalTrainerId") Long personalTrainerId, @RequestBody PersonalTrainerDTO personalTrainerDTO) throws PersonalTrainerNotFoundException, ParseException {
        personalTrainerService.updatePersonalTrainer(personalTrainerId, personalTrainerDTO);
        ApiResponseDTO apiResponseDTO = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_OK)
                .message("Personal trainer aggiornato con successo!")
                .build();
        return ResponseEntity.ok().body(apiResponseDTO);
    }
}

