package com.chess.model;

public class Rook extends Piece {

    public Rook(int row, int col, String color) {
        super(row, col, color);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        if (row != toRow && col != toCol) {
            return false; // Rook moves only in straight lines
        }

        // Check for obstacles in the path
        int stepRow = Integer.compare(toRow, row);
        int stepCol = Integer.compare(toCol, col);
        int currRow = row + stepRow;
        int currCol = col + stepCol;

        while (currRow != toRow || currCol != toCol) {
            if (board[currRow][currCol] != null) {
                return false; // Path is blocked
            }
            currRow += stepRow;
            currCol += stepCol;
        }

        // Check destination cell
        Piece target = board[toRow][toCol];
        return target == null || !target.getColor().equals(this.color);
    }

    @Override
    public String getType() {
        return "Rook";
    }
}


