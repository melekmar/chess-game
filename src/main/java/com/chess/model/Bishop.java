package com.chess.model;

public class Bishop extends Piece {

    public Bishop(int row, int col, String color) {
        super(row, col, color);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - row);
        int colDiff = Math.abs(toCol - col);
        if (rowDiff != colDiff) {
            return false; // Must move diagonally
        }

        int stepRow = (toRow - row) > 0 ? 1 : -1;
        int stepCol = (toCol - col) > 0 ? 1 : -1;
        int currRow = row + stepRow;
        int currCol = col + stepCol;

        while (currRow != toRow && currCol != toCol) {
            if (board[currRow][currCol] != null) {
                return false; // Path is blocked
            }
            currRow += stepRow;
            currCol += stepCol;
        }

        Piece destination = board[toRow][toCol];
        return destination == null || !destination.getColor().equals(this.color);
    }

    @Override
    public String getType() {
        return "Bishop";
    }
}
