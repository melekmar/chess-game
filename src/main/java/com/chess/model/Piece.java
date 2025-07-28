package com.chess.model;

/**
 * Abstract class representing a generic chess piece.
 * All specific piece types (e.g., Pawn, Rook, King) inherit from this class.
 */
public abstract class Piece {
    protected int row;     // Current row position of the piece on the board
    protected int col;     // Current column position of the piece on the board
    protected String color; // Piece color: either "WHITE" or "BLACK"

    // Constructor that sets the position and color of the piece
    public Piece(int row, int col, String color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }

    /**
     * Abstract method that must be implemented by each piece to define its valid movement.
     */
    public abstract boolean isValidMove(int toRow, int toCol, Piece[][] board);

    /**
     * Abstract method to return the type of the piece (e.g., "Pawn", "Knight", etc.).
     */
    public abstract String getType();

    // Getter for the current row
    public int getRow() {
        return row;
    }

    // Getter for the current column
    public int getCol() {
        return col;
    }

    // Getter for the piece's color
    public String getColor() {
        return color;
    }

    // Update the piece's position to a new row and column
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Override the toString method to return the color and type of the piece,
     * e.g., "WHITE King" or "BLACK Pawn"
     */
    @Override
    public String toString() {
        return color + " " + getType();
    }
}


