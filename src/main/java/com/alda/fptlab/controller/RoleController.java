package com.alda.fptlab.controller;

import com.alda.fptlab.dto.response.ApiResponseDTO;
import com.alda.fptlab.service.RoleService;
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
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping()
    ResponseEntity<ApiResponseDTO> fetchRoleList() {
        ApiResponseDTO apiResponseDTO = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_OK)
                .result(roleService.fetchRoleList())
                .build();
        return ResponseEntity.ok().body(apiResponseDTO);
    }
}
