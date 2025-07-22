package com.chess.model;

/**
 * Represents a Queen piece. Can move in straight lines or diagonals.
 */
public class Queen extends Piece {

    public Queen(int row, int col, String color) {
        super(row, col, color);
    }

    /**
     * Valid move for a Queen: any number of squares in straight line or diagonal.
     */
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - this.row);
        int colDiff = Math.abs(toCol - this.col);

        // Moves in straight lines (like rook) or diagonals (like bishop)
        return (this.row == toRow || this.col == toCol || rowDiff == colDiff);
    }

    @Override
    public String toString() {
        return this.color + " Queen";
    }
}

