package com.chess.model;

/**
 * Abstract class representing a chess piece.
 * Each piece has a position (row, col), a color, and can be a king.
 */
public abstract class Piece {
    protected int row;
    protected int col;
    protected String color;

    public Piece(int row, int col, String color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }

    // Abstract method to be implemented by each subclass to validate moves
    public abstract boolean isValidMove(int toRow, int toCol, Piece[][] board);

    // Getters
    public int getRow() { return row; }
    public int getCol() { return col; }
    public String getColor() { return color; }

    // Updates the position of the piece after a move
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Default display
    public String toString() {
        return this.color + " Piece";
    }
}
