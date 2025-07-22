package com.chess.model;

/**
 * Represents a Bishop piece in chess.
 * Bishops move diagonally any number of squares.
 * They cannot jump over other pieces.
 */
public class Bishop extends Piece {

    public Bishop(int row, int col, String color) {
        super(row, col, color);
    }

    /**
     * Validates Bishop's movement:
     * - Moves diagonally: abs(rowDiff) == abs(colDiff)
     * - Cannot jump over other pieces.
     * - Cannot capture piece of the same color.
     */
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = toRow - this.row;
        int colDiff = toCol - this.col;

        if (Math.abs(rowDiff) != Math.abs(colDiff)) return false;

        int rowStep = rowDiff > 0 ? 1 : -1;
        int colStep = colDiff > 0 ? 1 : -1;

        int currentRow = this.row + rowStep;
        int currentCol = this.col + colStep;

        // Check if the path is clear
        while (currentRow != toRow && currentCol != toCol) {
            if (board[currentRow][currentCol] != null) {
                return false;
            }
            currentRow += rowStep;
            currentCol += colStep;
        }

        // Final square: must be empty or enemy piece
        Piece destination = board[toRow][toCol];
        return destination == null || !destination.getColor().equals(this.color);
    }

    @Override
    public String toString() {
        return this.color + " Bishop";
    }
}

