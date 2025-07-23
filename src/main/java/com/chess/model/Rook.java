package com.chess.model;

public class Rook extends Piece {

    public Rook(int row, int col, String color) {
        super(row, col, color);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        if (row != toRow && col != toCol) return false; // Pas ligne ou colonne = invalide

        int rowStep = Integer.compare(toRow, row);
        int colStep = Integer.compare(toCol, col);

        int currentRow = row + rowStep;
        int currentCol = col + colStep;

        while (currentRow != toRow || currentCol != toCol) {
            if (board[currentRow][currentCol] != null) return false; // Obstacle détecté
            currentRow += rowStep;
            currentCol += colStep;
        }

        Piece destination = board[toRow][toCol];
        return destination == null || !destination.getColor().equals(color);
    }

    @Override
    public String getType() {
        return "Rook";
    }
}
