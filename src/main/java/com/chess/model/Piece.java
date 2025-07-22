package com.chess.model;

/**
 * Abstract class representing a chess piece.
 * Each piece has a position (row, col), a color, and must implement valid movement logic.
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

    // Abstract method for move validation
    public abstract boolean isValidMove(int toRow, int toCol, Piece[][] board);

    // Abstract method to return the type of piece (e.g., "Rook", "Pawn", etc.)
    public abstract String getType();

    // Getters
    public int getRow() { return row; }
    public int getCol() { return col; }
    public String getColor() { return color; }

    // Set new position
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Default toString returns type and color
    @Override
    public String toString() {
        return color + " " + getType();
    }
}

