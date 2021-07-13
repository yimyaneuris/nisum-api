package org.nisum.nisumapi.config;

import lombok.Getter;
import lombok.Setter;
import org.nisum.nisumapi.exceptions.BadRequestException;
import org.nisum.nisumapi.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionConfig {

    @Setter
    @Getter
    private class JsonErrorResponse {
        String message;

        public JsonErrorResponse() {
        }

        public JsonErrorResponse(String message) {
            super();
            this.message = message;
        }
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<JsonErrorResponse> productNotFound(UserNotFoundException e) {
        return new ResponseEntity<>(new JsonErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<JsonErrorResponse> badRequestException(BadRequestException e) {
        return new ResponseEntity<>(new JsonErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
