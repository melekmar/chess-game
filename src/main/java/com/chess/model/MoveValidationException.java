package com.chess.model;

/**
 * Custom exception used for invalid moves during a chess match.
 * It helps return clear and descriptive error messages when a move is not allowed.
 */
public class MoveValidationException extends RuntimeException {

    // Constructor that accepts a custom error message
    public MoveValidationException(String message) {
        super(message); // Pass the message to the parent RuntimeException
    }
}

