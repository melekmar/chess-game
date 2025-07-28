package com.chess.model;

/**
 * Represents the Bishop piece in chess.
 * A Bishop moves diagonally any number of squares without jumping over other pieces.
 */
public class Bishop extends Piece {

    // Constructor to initialize position and color
    public Bishop(int row, int col, String color) {
        super(row, col, color);
    }

    /**
     * Checks if a move is valid for a Bishop.
     * Bishops move diagonally only.
     */
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        int rowDiff = Math.abs(toRow - row);
        int colDiff = Math.abs(toCol - col);

        // Move must be exactly diagonal (equal row and column distance)
        if (rowDiff != colDiff) return false;

        // Determine direction of the move
        int rowStep = Integer.compare(toRow, row); // +1 or -1
        int colStep = Integer.compare(toCol, col); // +1 or -1

        int currentRow = row + rowStep;
        int currentCol = col + colStep;

        // Check if path is clear (no pieces in between)
        while (currentRow != toRow && currentCol != toCol) {
            if (board[currentRow][currentCol] != null) return false;
            currentRow += rowStep;
            currentCol += colStep;
        }

        // Allow move if destination is empty or contains opponent's piece
        Piece target = board[toRow][toCol];
        return target == null || !target.getColor().equals(color);
    }

    // Returns the type of this piece
    @Override
    public String getType() {
        return "Bishop";
    }
}

