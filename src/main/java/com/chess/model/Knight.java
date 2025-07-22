package com.chess.model;

/**
 * Represents a Knight piece. Moves in an "L" shape:
 * two squares in one direction and then one square perpendicular.
 * Can jump over other pieces.
 */
public class Knight extends Piece {

    public Knight(int row, int col, String color) {
        super(row, col, color);
    }

    /**
     * Valid move for a Knight: L-shaped moves.
     * Can jump over other pieces.
     */
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - this.row);
        int colDiff = Math.abs(toCol - this.col);

        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    @Override
    public String toString() {
        return this.color + " Knight";
    }
}
