package com.alda.fptlab.error;

import com.alda.fptlab.dto.ErrorResponseModel;
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
    public ResponseEntity<ErrorResponseModel> userNotFoundException (UserNotFoundException userNotFoundException, WebRequest request) {
        ErrorResponseModel errorMessage = new ErrorResponseModel(HttpStatus.NOT_FOUND, userNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponseModel> roleNotFoundException (RoleNotFoundException roleNotFoundException, WebRequest request) {
        ErrorResponseModel errorMessage = new ErrorResponseModel(HttpStatus.NOT_FOUND, roleNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
