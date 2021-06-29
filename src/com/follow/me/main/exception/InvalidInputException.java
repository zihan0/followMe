package com.follow.me.main.exception;

public class InvalidInputException extends Throwable {

    private String errorMessage;

    public InvalidInputException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
