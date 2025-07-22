package com.chess.model;

// Abstract class representing any chess piece
public abstract class Piece {
    protected int row;         // Current row position (0–7)
    protected int col;         // Current column position (0–7)
    protected String color;    // "White" or "Black"
    protected String name;     // Piece type: King, Queen, etc.

    // Constructor for all pieces
    public Piece(int row, int col, String color, String name) {
        this.row = row;
        this.col = col;
        this.color = color;
        this.name = name;
    }

    // Abstract method to be implemented by each subclass
    public abstract boolean isValidMove(int toRow, int toCol, Piece[][] board);

    // Getters
    public int getRow() { return row; }
    public int getCol() { return col; }
    public String getColor() { return color; }
    public String getName() { return name; }

    // Set new position
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Returns a symbolic representation (e.g., "wK" or "bP")
    public String toString() {
        return (color.charAt(0) + name.substring(0, 1)).toUpperCase();
    }
}
