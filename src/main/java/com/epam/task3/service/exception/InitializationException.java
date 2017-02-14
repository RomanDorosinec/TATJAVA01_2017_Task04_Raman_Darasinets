package com.epam.task3.service.exception;


public class InitializationException extends Exception {
    public InitializationException(String message) {
        super(message);
    }

    public InitializationException(Exception e) {
        super(e);
    }
}
