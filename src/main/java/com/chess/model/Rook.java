package com.chess.model;

/**
 * Represents the Rook piece in chess.
 * The Rook moves in a straight line horizontally or vertically.
 */
public class Rook extends Piece {

    // Constructor to initialize the Rook's position and color
    public Rook(int row, int col, String color) {
        super(row, col, color);
    }

    /**
     * Validates if the Rook can legally move to the target position.
     * Rook can move only in straight lines (row or column must stay the same).
     * It cannot jump over other pieces.
     */
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        // Move must be either in the same row or same column
        if (row != toRow && col != toCol) return false;

        // Determine direction of movement
        int rowStep = Integer.compare(toRow, row);
        int colStep = Integer.compare(toCol, col);

        int currentRow = row + rowStep;
        int currentCol = col + colStep;

        // Check for obstacles along the path
        while (currentRow != toRow || currentCol != toCol) {
            if (board[currentRow][currentCol] != null) return false;
            currentRow += rowStep;
            currentCol += colStep;
        }

        // Allow move if destination is empty or has opponent's piece
        Piece destination = board[toRow][toCol];
        return destination == null || !destination.getColor().equals(color);
    }

    // Returns the piece type as a string
    @Override
    public String getType() {
        return "Rook";
    }
}

