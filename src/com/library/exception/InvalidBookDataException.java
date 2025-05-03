package com.library.exception;

public class InvalidBookDataException extends Exception {
    public InvalidBookDataException(String message) {
        super(message);
    }
}