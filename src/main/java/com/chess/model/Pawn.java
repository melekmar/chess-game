package com.chess.model;

public class Pawn extends Piece {

    public Pawn(int row, int col, String color) {
        super(row, col, color);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int direction = color.equals("WHITE") ? -1 : 1;  // WHITE moves up, BLACK moves down
        int rowDiff = toRow - row;
        int colDiff = toCol - col;

        // Check boundaries
        if (toRow < 0 || toRow >= 8 || toCol < 0 || toCol >= 8) {
            return false;
        }

        // Moving forward
        if (colDiff == 0) {
            if (rowDiff == direction && board[toRow][toCol] == null) {
                return true;
            }
            if ((color.equals("WHITE") && row == 6 || color.equals("BLACK") && row == 1)
                    && rowDiff == 2 * direction
                    && board[row + direction][col] == null
                    && board[toRow][toCol] == null) {
                return true;
            }
        }

        // Capturing diagonally
        if (Math.abs(colDiff) == 1 && rowDiff == direction) {
            Piece target = board[toRow][toCol];
            if (target != null && !target.getColor().equals(this.color)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getType() {
        return "Pawn";
    }
}

