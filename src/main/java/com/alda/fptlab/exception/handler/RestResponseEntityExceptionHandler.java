package com.alda.fptlab.exception.handler;

import com.alda.fptlab.dto.response.ApiResponseDTO;
import com.alda.fptlab.dto.response.ValidationErrorDTO;
import com.alda.fptlab.exception.RoleNotFoundException;
import com.alda.fptlab.exception.UserAlreadyExistException;
import com.alda.fptlab.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiResponseDTO> userAlreadyExistException (UserAlreadyExistException userAlreadyExistException, WebRequest request) {
        var error = ApiResponseDTO.builder()
                .status(HttpServletResponse.SC_BAD_REQUEST)
                .message(userAlreadyExistException.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ValidationErrorDTO> validationErrorDetails = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> mapToErrorMessageDto(error))
                .collect(Collectors.toList());

        ApiResponseDTO apiResponseDTO = ApiResponseDTO.builder()
                .status(status.value())
                .message(status.name())
                .result(validationErrorDetails)
                .build();
        return new ResponseEntity<>(apiResponseDTO,status);

    }

    private ValidationErrorDTO mapToErrorMessageDto(ObjectError error) {
        String fieldError = "";
        String rejectedValue = "";
        if(error instanceof FieldError) {
            fieldError = ((FieldError) error).getField();
            rejectedValue = (String)((FieldError) error).getRejectedValue();
        }
        return new ValidationErrorDTO(error.getObjectName(),fieldError,error.getDefaultMessage(),rejectedValue);
    }
}
