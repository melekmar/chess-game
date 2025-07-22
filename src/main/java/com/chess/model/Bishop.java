package com.chess.model;

/**
 * Represents a Bishop piece. Moves diagonally on the board.
 */
public class Bishop extends Piece {

    public Bishop(int row, int col, String color) {
        super(row, col, color);
    }

    /**
     * Valid move for a Bishop: moves diagonally any number of squares
     * without jumping over other pieces.
     */
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - this.row);
        int colDiff = Math.abs(toCol - this.col);

        // Must move diagonally
        if (rowDiff != colDiff) {
            return false;
        }

        int rowDirection = Integer.compare(toRow, this.row);
        int colDirection = Integer.compare(toCol, this.col);

        int currentRow = this.row + rowDirection;
        int currentCol = this.col + colDirection;

        // Check each square along the path for blocking pieces
        while (currentRow != toRow && currentCol != toCol) {
            if (board[currentRow][currentCol] != null) {
                return false; // Path is blocked
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }

        return true;
    }

    @Override
    public String toString() {
        return this.color + " Bishop";
    }
}
