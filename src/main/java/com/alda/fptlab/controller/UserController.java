package com.alda.fptlab.controller;


import com.alda.fptlab.dto.response.ApiResponseDTO;
import com.alda.fptlab.exception.RoleNotFoundException;
import com.alda.fptlab.exception.SubscriptionAlreadyActiveException;
import com.alda.fptlab.exception.SubscriptionTypeNotFoundException;
import com.alda.fptlab.exception.UserNotFoundException;
import com.alda.fptlab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<ApiResponseDTO> fetchUserList() {
        ApiResponseDTO apiResponseDTO = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_OK)
                .result(userService.fetchUserList())
                .build();
        return ResponseEntity.ok().body(apiResponseDTO);
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponseDTO> fetchUser(Principal principal) throws UserNotFoundException {
        ApiResponseDTO apiResponseDTO = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_OK)
                .result(userService.fetchUser(principal))
                .build();
        return ResponseEntity.ok().body(apiResponseDTO);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseDTO> fetchUserById(@PathVariable("userId") Long userId) throws UserNotFoundException {
        ApiResponseDTO apiResponseDTO = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_OK)
                .result(userService.fetchUserById(userId))
                .build();
        return ResponseEntity.ok().body(apiResponseDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{userId}/subscriptions-type/{subTypeId}")
    public ResponseEntity<ApiResponseDTO> updateUserWithSubscription(@PathVariable("userId") Long userId, @PathVariable("subTypeId") Long subTypeId) throws UserNotFoundException, SubscriptionTypeNotFoundException, SubscriptionAlreadyActiveException {
        userService.updateUserWithSubscription(userId,subTypeId);
        ApiResponseDTO apiResponseDTO = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_OK)
                .message("Utente aggiornato con successo!")
                .build();
        return ResponseEntity.ok().body(apiResponseDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<?> updateUserWithRole(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId) throws UserNotFoundException, RoleNotFoundException {
        userService.updateUserWithRole(userId, roleId);
        return ResponseEntity.ok().build();
    }

}
