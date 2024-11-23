package com.example.text.exception;
public class UsernameException extends RuntimeException {
    private final String message;

    public UsernameException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;       //if user not found
    }
}
