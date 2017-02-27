package com.epam.task3.controller.command.exception;

public class IllegalNumberParam extends Exception {
    public IllegalNumberParam(Exception e) {
        super(e);
    }

    public IllegalNumberParam(String message) {
        super(message);
    }
}
