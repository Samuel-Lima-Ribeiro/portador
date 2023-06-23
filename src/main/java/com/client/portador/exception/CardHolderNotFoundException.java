package com.client.portador.exception;

public class CardHolderNotFoundException extends RuntimeException {
    public CardHolderNotFoundException(String message) {
        super(message);
    }
}
