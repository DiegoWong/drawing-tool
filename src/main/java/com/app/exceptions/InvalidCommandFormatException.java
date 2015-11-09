package com.app.exceptions;

public class InvalidCommandFormatException extends  RuntimeException{
    public InvalidCommandFormatException(String msg) {
        super(msg);
    }
}
