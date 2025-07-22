package com.chess.model;

/**
 * Represents a Knight piece in chess.
 * Knights move in an "L" shape: two squares in one direction and one square perpendicular.
 * Knights can jump over other pieces.
 */
public class Knight extends Piece {

    public Knight(int row, int col, String color) {
        super(row, col, color);
    }

    /**
     * Validates Knight's movement:
     * - Moves in an L-shape: (2,1) or (1,2) in any direction.
     * - Can jump over other pieces.
     * - Cannot capture a piece of the same color.
     */
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - this.row);
        int colDiff = Math.abs(toCol - this.col);

        boolean isLShape = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);

        if (!isLShape) return false;

        Piece destination = board[toRow][toCol];
        return destination == null || !destination.getColor().equals(this.color);
    }

    @Override
    public String toString() {
        return this.color + " Knight";
    }
}
