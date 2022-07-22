package com.alda.fptlab.controller;

import com.alda.fptlab.dto.response.ApiResponseDTO;
import com.alda.fptlab.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("")
    public ResponseEntity<ApiResponseDTO> fetchSubscriptionList() {
        ApiResponseDTO apiResponseDTO = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_OK)
                .result(subscriptionService.fetchSubscriptionList())
                .build();

        return ResponseEntity.ok().body(apiResponseDTO);
    }
}
