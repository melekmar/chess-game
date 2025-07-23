package com.chess.model;

public class MoveValidationException extends RuntimeException {
    public MoveValidationException(String message) {
        super(message);
    }
}
