package com.chess.model;

/**
 * Represents a Pawn chess piece.
 * Handles its unique movement and capture rules.
 */
public class Pawn extends Piece {

    // Constructor: sets the position and color of the pawn
    public Pawn(int row, int col, String color) {
        super(row, col, color);
    }

    /**
     * Checks if a pawn move is valid according to chess rules.
     */
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        // Determine the direction of movement based on pawn color
        int direction = color.equals("WHITE") ? -1 : 1; // WHITE goes up, BLACK goes down
        int rowDiff = toRow - row;
        int colDiff = toCol - col;

        // Prevent moves outside the board
        if (toRow < 0 || toRow >= 8 || toCol < 0 || toCol >= 8) {
            return false;
        }

        // Standard move: forward by one square
        if (colDiff == 0) {
            if (rowDiff == direction && board[toRow][toCol] == null) {
                return true;
            }

            // Initial double move: two squares forward from starting position
            if ((color.equals("WHITE") && row == 6 || color.equals("BLACK") && row == 1)
                    && rowDiff == 2 * direction
                    && board[row + direction][col] == null
                    && board[toRow][toCol] == null) {
                return true;
            }
        }

        // Diagonal capture move
        if (Math.abs(colDiff) == 1 && rowDiff == direction) {
            Piece target = board[toRow][toCol];
            // Must capture opponent's piece
            if (target != null && !target.getColor().equals(this.color)) {
                return true;
            }
        }

        // All other moves are invalid
        return false;
    }

    // Return the piece type as a string
    @Override
    public String getType() {
        return "Pawn";
    }
}

