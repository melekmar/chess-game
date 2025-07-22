package com.chess.model;

/**
 * Abstract class representing a chess piece.
 * Each piece has a position (row, col) and a color.
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

    // Abstract method: to be implemented by subclasses to define their valid moves
    public abstract boolean isValidMove(int toRow, int toCol, Piece[][] board);

    public int getRow() { return row; }
    public int getCol() { return col; }
    public String getColor() { return color; }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return color + " Piece";
    }
}

