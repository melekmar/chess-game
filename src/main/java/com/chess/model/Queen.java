package com.chess.model;

/**
 * Represents the Queen piece in chess.
 * The Queen can move like both a Rook and a Bishop — in straight lines and diagonals.
 */
public class Queen extends Piece {

    // Constructor to initialize the Queen with position and color
    public Queen(int row, int col, String color) {
        super(row, col, color);
    }

    /**
     * Checks if a move to the given position is valid for a Queen.
     * A Queen can move any number of squares in any direction — horizontally, vertically, or diagonally.
     */
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - row);
        int colDiff = Math.abs(toCol - col);

        // If moving along the same row or column → delegate to Rook logic
        if (row == toRow || col == toCol) {
            return new Rook(row, col, color).isValidMove(toRow, toCol, board);
        }
        // If moving diagonally → delegate to Bishop logic
        else if (rowDiff == colDiff) {
            return new Bishop(row, col, color).isValidMove(toRow, toCol, board);
        }

        // Invalid move
        return false;
    }

    // Returns the type of the piece as a string
    @Override
    public String getType() {
        return "Queen";
    }
}


