package com.alda.fptlab.exception.handler;

import com.alda.fptlab.dto.response.ApiResponseDTO;
import com.alda.fptlab.exception.RoleNotFoundException;
import com.alda.fptlab.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponseDTO> userNotFoundException (UserNotFoundException userNotFoundException, WebRequest request) {
        var error = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_NOT_FOUND)
                .message(userNotFoundException.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ApiResponseDTO> roleNotFoundException (RoleNotFoundException roleNotFoundException, WebRequest request) {
        var error = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_NOT_FOUND)
                .message(roleNotFoundException.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
