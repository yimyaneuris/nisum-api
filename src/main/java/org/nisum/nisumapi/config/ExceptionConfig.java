package org.nisum.nisumapi.config;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.Getter;
import lombok.Setter;
import org.nisum.nisumapi.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionConfig {

    @Setter @Getter
    private static class ApiError {
        String message;

        public ApiError(String message) {
            super();
            this.message = message;
        }
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ApiError> badRequestException(BadRequestException e) {
        return new ResponseEntity<>(new ApiError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ApiError> resourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new ApiError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    protected ResponseEntity<ApiError> internalServerErrorException(InternalServerErrorException e) {
        return new ResponseEntity<>(new ApiError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JsonMappingException.class)
    protected ResponseEntity<ApiError> jsonMappingException(JsonMappingException e) {
        return new ResponseEntity<>(new ApiError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
