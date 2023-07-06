package com.client.portador.exception;

public class LimitInvalidException extends RuntimeException {
    public LimitInvalidException(String message) {
        super(message);
    }
}
