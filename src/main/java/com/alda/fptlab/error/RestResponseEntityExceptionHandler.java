package com.alda.fptlab.error;

import com.alda.fptlab.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> userNotFoundException (UserNotFoundException userNotFoundException, WebRequest request) {
        ErrorResponseDTO errorMessage = new ErrorResponseDTO(HttpStatus.NOT_FOUND, userNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> roleNotFoundException (RoleNotFoundException roleNotFoundException, WebRequest request) {
        ErrorResponseDTO errorMessage = new ErrorResponseDTO(HttpStatus.NOT_FOUND, roleNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
