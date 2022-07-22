package com.alda.fptlab.controller;

import com.alda.fptlab.dto.response.ApiResponseDTO;
import com.alda.fptlab.service.SubscriptionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriptions-type")
public class SubscriptionTypeController {

    private final SubscriptionTypeService subscriptionTypeService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<ApiResponseDTO> fetchSubscriptionTypeList() {
        ApiResponseDTO apiResponseDTO = ApiResponseDTO.builder()
                .result(HttpServletResponse.SC_OK)
                .result(subscriptionTypeService.fetchSubscriptionTypeList())
                .build();
        return ResponseEntity.ok().body(apiResponseDTO);
    }
}
