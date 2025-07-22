package com.chess.model;

public class Queen extends Piece {

    public Queen(int row, int col, String color) {
        super(row, col, color);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = toRow - row;
        int colDiff = toCol - col;

        // Check if the move is in a straight line or diagonal
        if (row == toRow || col == toCol || Math.abs(rowDiff) == Math.abs(colDiff)) {
            int rowDirection = Integer.compare(rowDiff, 0);
            int colDirection = Integer.compare(colDiff, 0);

            int currentRow = row + rowDirection;
            int currentCol = col + colDirection;

            // Check each square along the path for obstructions
            while (currentRow != toRow || currentCol != toCol) {
                if (board[currentRow][currentCol] != null) return false;
                currentRow += rowDirection;
                currentCol += colDirection;
            }

            // Check the target square
            Piece target = board[toRow][toCol];
            return target == null || !target.getColor().equals(color);
        }

        return false;
    }

    @Override
    public String getType() {
        return "Queen";
    }
}

