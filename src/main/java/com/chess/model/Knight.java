package com.chess.model;

public class Knight extends Piece {

    public Knight(int row, int col, String color) {
        super(row, col, color);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - row);
        int colDiff = Math.abs(toCol - col);

        // Valid knight move: L-shape (2 by 1 or 1 by 2)
        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            Piece destination = board[toRow][toCol];
            return destination == null || !destination.getColor().equals(this.color);
        }

        return false;
    }

    @Override
    public String getType() {
        return "Knight";
    }
}
