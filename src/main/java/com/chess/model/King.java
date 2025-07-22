package com.chess.model;

// King is a subclass of Piece
public class King extends Piece {

    // Constructor for King
    public King(int row, int col, String color) {
        super(row, col, color, "King");
    }

    // Logic for valid King moves
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - row);   // Vertical movement
        int colDiff = Math.abs(toCol - col);   // Horizontal movement

        // King moves only 1 square in any direction
        if (rowDiff <= 1 && colDiff <= 1) {
            // Check if destination is empty or has opponent's piece
            Piece destination = board[toRow][toCol];
            return destination == null || !destination.getColor().equals(this.color);
        }
        return false;  // Any other move is invalid
    }
}
