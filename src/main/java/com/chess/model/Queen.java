package com.chess.model;

public class Queen extends Piece {

    public Queen(int row, int col, String color) {
        super(row, col, color);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - row);
        int colDiff = Math.abs(toCol - col);

        if (row == toRow || col == toCol) {
            return new Rook(row, col, color).isValidMove(toRow, toCol, board);
        } else if (rowDiff == colDiff) {
            return new Bishop(row, col, color).isValidMove(toRow, toCol, board);
        }

        return false;
    }

    @Override
    public String getType() {
        return "Queen";
    }
}

