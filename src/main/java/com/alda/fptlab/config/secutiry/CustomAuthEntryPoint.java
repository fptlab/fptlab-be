package com.alda.fptlab.config.secutiry;

import com.alda.fptlab.dto.response.ApiResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("Unauthorized error: {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiResponseDTO apiResponseDTO = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_UNAUTHORIZED)
                .message(authException.getMessage()) //TODO cambiare messaggio per bad credential
                .build();

        new ObjectMapper().writeValue(response.getOutputStream(), apiResponseDTO);
    }
}
