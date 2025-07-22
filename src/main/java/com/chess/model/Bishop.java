package com.chess.model;

/**
 * Represents a Bishop piece. Moves any number of squares diagonally,
 * as long as the path is clear.
 */
public class Bishop extends Piece {

    public Bishop(int row, int col, String color) {
        super(row, col, color);
    }

    /**
     * Validates the move:
     * - Must be a diagonal move.
     * - Path must be clear (no pieces in between).
     */
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - this.row);
        int colDiff = Math.abs(toCol - this.col);

        // Must move diagonally
        if (rowDiff != colDiff) {
            return false;
        }

        int rowStep = (toRow - this.row) / rowDiff;
        int colStep = (toCol - this.col) / colDiff;

        // Check each cell along the path
        int currentRow = this.row + rowStep;
        int currentCol = this.col + colStep;
        while (currentRow != toRow && currentCol != toCol) {
            if (board[currentRow][currentCol] != null) {
                return false; // Path blocked
            }
            currentRow += rowStep;
            currentCol += colStep;
        }

        return true;
    }

    @Override
    public String toString() {
        return this.color + " Bishop";
    }
}

