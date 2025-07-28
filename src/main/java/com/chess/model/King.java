package com.chess.model;

/**
 * Represents the King piece in chess.
 * The King can move one square in any direction.
 */
public class King extends Piece {

    // Constructor assigns initial position and color
    public King(int row, int col, String color) {
        super(row, col, color);
    }

    /**
     * Validates the move for the King.
     * It can move only one square in any direction.
     */
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - row); // vertical movement
        int colDiff = Math.abs(toCol - col); // horizontal movement

        // King can move one square in any direction
        if (rowDiff <= 1 && colDiff <= 1) {
            Piece target = board[toRow][toCol];
            // Move is valid if the target is empty or contains opponent's piece
            return target == null || !target.getColor().equals(color);
        }

        return false;
    }

    // Returns the type of the piece as a string
    @Override
    public String getType() {
        return "King";
    }
}

