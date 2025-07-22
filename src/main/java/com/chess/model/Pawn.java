package com.chess.model;

public class Pawn extends Piece {

    public Pawn(int row, int col, String color) {
        super(row, col, color);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int direction = color.equals("WHITE") ? -1 : 1;  // White moves up, Black moves down
        int rowDiff = toRow - row;
        int colDiff = Math.abs(toCol - col);

        // Move forward
        if (colDiff == 0 && board[toRow][toCol] == null) {
            if (rowDiff == direction) return true;

            // First move: two squares
            if ((color.equals("WHITE") && row == 6 || color.equals("BLACK") && row == 1) &&
                rowDiff == 2 * direction && board[row + direction][col] == null) {
                return true;
            }
        }

        // Capture
        if (colDiff == 1 && rowDiff == direction && board[toRow][toCol] != null &&
            !board[toRow][toCol].getColor().equals(color)) {
            return true;
        }

        return false;
    }

    @Override
    public String getType() {
        return "Pawn";
    }
}

