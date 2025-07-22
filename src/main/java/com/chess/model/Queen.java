package com.chess.model;

// Queen is a subclass of Piece
public class Queen extends Piece {

    // Constructor for Queen
    public Queen(int row, int col, String color) {
        super(row, col, color, "Queen");
    }

    // Logic for valid Queen moves
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = toRow - row;
        int colDiff = toCol - col;

        // Queen moves like Rook or Bishop: straight or diagonal
        if (Math.abs(rowDiff) == Math.abs(colDiff) || row == toRow || col == toCol) {
            int stepRow = Integer.compare(rowDiff, 0);  // 1, 0, or -1
            int stepCol = Integer.compare(colDiff, 0);

            int r = row + stepRow;
            int c = col + stepCol;

            // Check path is clear (no pieces in the way)
            while (r != toRow || c != toCol) {
                if (board[r][c] != null) return false;
                r += stepRow;
                c += stepCol;
            }

            // Destination is empty or has enemy piece
            Piece destination = board[toRow][toCol];
            return destination == null || !destination.getColor().equals(this.color);
        }

        return false;  // Invalid move otherwise
    }
}
