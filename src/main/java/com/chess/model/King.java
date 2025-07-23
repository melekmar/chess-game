package com.chess.model;

public class King extends Piece {

    public King(int row, int col, String color) {
        super(row, col, color);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - row);
        int colDiff = Math.abs(toCol - col);

        if (rowDiff <= 1 && colDiff <= 1) {
            Piece target = board[toRow][toCol];
            return target == null || !target.getColor().equals(color);
        }

        return false;
    }

    @Override
    public String getType() {
        return "King";
    }
}
