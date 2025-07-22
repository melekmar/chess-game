package com.chess.model;

/**
 * Represents a Rook piece. Moves in straight lines only.
 */
public class Rook extends Piece {

    public Rook(int row, int col, String color) {
        super(row, col, color);
    }

    /**
     * Rook can move horizontally or vertically, any number of squares,
     * but we are not checking obstacles yet.
     */
    @Override
    public boolean isValidMove(int toRow, int toCol, Piece[][] board) {
        return this.row == toRow || this.col == toCol;
    }

    @Override
    public String toString() {
        return this.color + " Rook";
    }
}
