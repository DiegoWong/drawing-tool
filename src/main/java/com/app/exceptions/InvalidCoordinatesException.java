package com.app.exceptions;

public class InvalidCoordinatesException extends RuntimeException{
    public InvalidCoordinatesException(String message) {
        super(message);
    }
}
