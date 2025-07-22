package com.chess.model;

/**
 * Represents a Pawn piece. It moves forward (1 or 2 steps on first move),
 * and captures diagonally. Includes promotion and en passant handling later.
 */
public class Pawn extends Piece {

    public Pawn(int row, int col, String color) {
        super(row, col, color);
    }

    /**
     * Validates pawn movement:
     * - Can move 1 step forward if the square is empty.
     * - Can move 2 steps forward from starting position if both squares are empty.
     * - Can capture diagonally if opponent's piece exists on the destination.
     */
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int direction = this.color.equals("White") ? -1 : 1;
        int rowDiff = toRow - this.row;
        int colDiff = Math.abs(toCol - this.col);

        // Move forward (no horizontal move)
        if (colDiff == 0) {
            // Move 1 square forward
            if (rowDiff == direction && board[toRow][toCol] == null) {
                return true;
            }

            // Move 2 squares forward on first move
            if ((this.color.equals("White") && this.row == 6 || this.color.equals("Black") && this.row == 1)
                    && rowDiff == 2 * direction
                    && board[this.row + direction][this.col] == null
                    && board[toRow][toCol] == null) {
                return true;
            }
        }

        // Diagonal capture
        if (colDiff == 1 && rowDiff == direction && board[toRow][toCol] != null
                && !board[toRow][toCol].getColor().equals(this.color)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return this.color + " Pawn";
    }
}

