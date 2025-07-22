package com.chess.model;

/**
 * Represents a Rook piece. Moves in straight lines (horizontal or vertical).
 */
public class Rook extends Piece {

    public Rook(int row, int col, String color) {
        super(row, col, color);
    }

    /**
     * Valid move for a Rook: any number of squares in straight lines
     * without jumping over pieces.
     */
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        if (this.row != toRow && this.col != toCol) {
            return false; // Not a straight line
        }

        int rowDirection = Integer.compare(toRow, this.row);
        int colDirection = Integer.compare(toCol, this.col);

        int currentRow = this.row + rowDirection;
        int currentCol = this.col + colDirection;

        // Check if there are pieces blocking the path
        while (currentRow != toRow || currentCol != toCol) {
            if (board[currentRow][currentCol] != null) {
                return false; // Blocked path
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }

        return true;
    }

    @Override
    public String toString() {
        return this.color + " Rook";
    }
}

