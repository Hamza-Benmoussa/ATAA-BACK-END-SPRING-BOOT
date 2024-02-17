package com.example.ataaspringbootangular.exception.except;

public class EmailDejaExisteException extends RuntimeException {
    public EmailDejaExisteException(String message) {
        super(message);
    }
}