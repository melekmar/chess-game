package com.chess.model;

public class Knight extends Piece {

    public Knight(int row, int col, String color) {
        super(row, col, color);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - row);
        int colDiff = Math.abs(toCol - col);

        if (!((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))) {
            return false;
        }

        Piece target = board[toRow][toCol];
        return target == null || !target.getColor().equals(color);
    }

    @Override
    public String getType() {
        return "Knight";
    }
}
