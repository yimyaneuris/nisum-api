package org.nisum.nisumapi.exceptions;

public class BadEmailException extends RuntimeException{

    public BadEmailException() {
    }

    public BadEmailException(String message) {
        super(message);
    }
}
