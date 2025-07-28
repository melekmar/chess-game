package com.chess.model;

/**
 * Represents the 8x8 chess board.
 * Manages placement, movement, and display of pieces on the board.
 */
public class Board {
    private Piece[][] grid; // 2D array representing the board's state

    // Constructor initializes an empty 8x8 grid
    public Board() {
        grid = new Piece[8][8];
    }

    // Returns the piece located at the given row and column
    public Piece getPiece(int row, int col) {
        return grid[row][col];
    }

    /**
     * Moves a piece from one position to another.
     * Updates the piece’s position and clears the original cell.
     */
    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        Piece moving = grid[fromRow][fromCol];
        if (moving != null) {
            moving.setPosition(toRow, toCol);          // Update piece's position
            grid[toRow][toCol] = moving;               // Move piece to new cell
            grid[fromRow][fromCol] = null;             // Clear old cell
        }
    }

    // Places a specific piece at the given location on the board
    public void setPiece(int row, int col, Piece piece) {
        grid[row][col] = piece;
    }

    // Returns the entire 2D grid (board)
    public Piece[][] getGrid() {
        return grid;
    }

    /**
     * Prints the current board state to the console.
     * Used for debugging and visualization.
     */
    public void printBoard() {
        System.out.println("    A   B   C   D   E   F   G   H");
        System.out.println("  +---+---+---+---+---+---+---+---+");
        for (int row = 0; row < 8; row++) {
            System.out.print((8 - row) + " |");
            for (int col = 0; col < 8; col++) {
                if (grid[row][col] == null) {
                    System.out.print("   |"); // Empty cell
                } else {
                    String symbol = grid[row][col].toString().substring(0, 2); // Show short piece info
                    System.out.print(" " + symbol + "|");
                }
            }
            System.out.println(" " + (8 - row));
            System.out.println("  +---+---+---+---+---+---+---+---+");
        }
        System.out.println("    A   B   C   D   E   F   G   H");
    }
}

