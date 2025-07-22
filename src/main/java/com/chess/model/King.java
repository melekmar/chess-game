package com.chess.model;

/**
 * Represents a King piece. Can move one square in any direction.
 */
public class King extends Piece {

    public King(int row, int col, String color) {
        super(row, col, color);
    }

    /**
     * Valid move for a King: one square in any direction.
     */
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - this.row);
        int colDiff = Math.abs(toCol - this.col);

        // Can only move one square in any direction
        return (rowDiff <= 1 && colDiff <= 1);
    }

    @Override
    public String toString() {
        return this.color + " King";
    }
}
