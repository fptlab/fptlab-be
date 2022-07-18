package com.alda.fptlab.exception.handler;

import com.alda.fptlab.dto.error.GenericErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;

@ControllerAdvice
@Slf4j
public class AuthExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleAuthenticationException(BadCredentialsException badCredentialsException, WebRequest request) {
        var error = GenericErrorDTO.builder()
                .message(String.format("BadCredentials: %s", badCredentialsException.getMessage()))
                .status(HttpServletResponse.SC_NOT_FOUND)
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException authenticationException, WebRequest request) {
        var error = GenericErrorDTO.builder()
                .message(String.format("AuthenticationException: %s", authenticationException.getMessage()))
                .status(HttpServletResponse.SC_BAD_REQUEST)
                .build();

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Object> handleSignatureException(SignatureException signatureException, WebRequest request) {
        var error = GenericErrorDTO.builder()
                .message(String.format("Invalid JWT signature: %s", signatureException.getMessage()))
                .status(HttpServletResponse.SC_BAD_REQUEST)
                .build();

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
