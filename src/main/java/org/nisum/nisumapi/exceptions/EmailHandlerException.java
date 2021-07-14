package org.nisum.nisumapi.exceptions;

public class EmailHandlerException extends RuntimeException{

    public EmailHandlerException() {
    }

    public EmailHandlerException(String message) {
        super(message);
    }
}
