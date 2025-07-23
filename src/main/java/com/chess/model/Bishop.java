package com.chess.model;

public class Bishop extends Piece {

    public Bishop(int row, int col, String color) {
        super(row, col, color);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - row);
        int colDiff = Math.abs(toCol - col);

        if (rowDiff != colDiff) return false;

        int rowStep = Integer.compare(toRow, row);
        int colStep = Integer.compare(toCol, col);

        int currentRow = row + rowStep;
        int currentCol = col + colStep;

        while (currentRow != toRow && currentCol != toCol) {
            if (board[currentRow][currentCol] != null) return false;
            currentRow += rowStep;
            currentCol += colStep;
        }

        Piece target = board[toRow][toCol];
        return target == null || !target.getColor().equals(color);
    }

    @Override
    public String getType() {
        return "Bishop";
    }
}
