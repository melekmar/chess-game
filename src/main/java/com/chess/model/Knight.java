package com.chess.model;

/**
 * Represents the Knight piece in chess.
 * The Knight moves in an "L" shape: 2 squares in one direction and then 1 square perpendicular.
 * It can jump over other pieces.
 */
public class Knight extends Piece {

    // Constructor sets the initial position and color
    public Knight(int row, int col, String color) {
        super(row, col, color);
    }

    /**
     * Validates the move for the Knight.
     * Knight must move in an L-shape: (2,1) or (1,2).
     */
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - row);
        int colDiff = Math.abs(toCol - col);

        // Check if move is a valid L-shape
        if (!((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))) {
            return false;
        }

        // Check if destination is empty or contains an opponent piece
        Piece target = board[toRow][toCol];
        return target == null || !target.getColor().equals(color);
    }

    // Returns the type of the piece as a string
    @Override
    public String getType() {
        return "Knight";
    }
}

