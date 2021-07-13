package org.nisum.nisumapi.exceptions;

public class BadPasswordException extends RuntimeException{

    public BadPasswordException() {
    }

    public BadPasswordException(String message) {
        super(message);
    }
}
