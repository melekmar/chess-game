package com.chess.model;

/**
 * Represents the 8x8 chess board.
 * Manages piece placement, retrieval, and board state.
 */
public class Board {
    private Piece[][] grid; // 2D array representing the chess board

    public Board() {
        grid = new Piece[8][8];
        initializeBoard(); // Set up initial piece positions
    }

    /**
     * Places all pieces in their initial positions at game start.
     */
    private void initializeBoard() {
        // Place black major and minor pieces (row 0)
        grid[0][0] = new Rook(0, 0, "BLACK");
        grid[0][1] = new Knight(0, 1, "BLACK");
        grid[0][2] = new Bishop(0, 2, "BLACK");
        grid[0][3] = new Queen(0, 3, "BLACK");
        grid[0][4] = new King(0, 4, "BLACK");
        grid[0][5] = new Bishop(0, 5, "BLACK");
        grid[0][6] = new Knight(0, 6, "BLACK");
        grid[0][7] = new Rook(0, 7, "BLACK");

        // Place black pawns (row 1)
        for (int i = 0; i < 8; i++) {
            grid[1][i] = new Pawn(1, i, "BLACK");
        }

        // Place white major and minor pieces (row 7)
        grid[7][0] = new Rook(7, 0, "WHITE");
        grid[7][1] = new Knight(7, 1, "WHITE");
        grid[7][2] = new Bishop(7, 2, "WHITE");
        grid[7][3] = new Queen(7, 3, "WHITE");
        grid[7][4] = new King(7, 4, "WHITE");
        grid[7][5] = new Bishop(7, 5, "WHITE");
        grid[7][6] = new Knight(7, 6, "WHITE");
        grid[7][7] = new Rook(7, 7, "WHITE");

        // Place white pawns (row 6)
        for (int i = 0; i < 8; i++) {
            grid[6][i] = new Pawn(6, i, "WHITE");
        }
    }

    /**
     * Retrieves the piece at a given location.
     */
    public Piece getPiece(int row, int col) {
        return grid[row][col];
    }

    /**
     * Moves a piece from one location to another.
     */
    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        Piece moving = grid[fromRow][fromCol];
        if (moving != null) {
            moving.setPosition(toRow, toCol); // update internal position
            grid[toRow][toCol] = moving;      // place piece in new location
            grid[fromRow][fromCol] = null;    // clear old location
        }
    }

    /**
     * Sets a piece directly at a given board location.
     */
    public void setPiece(int row, int col, Piece piece) {
        grid[row][col] = piece;
    }

    /**
     * Displays the current board state in a user-friendly format.
     */
    public void printBoard() {
        System.out.println("    A   B   C   D   E   F   G   H");
        System.out.println("  +---+---+---+---+---+---+---+---+");
        for (int row = 0; row < 8; row++) {
            System.out.print((8 - row) + " |");
            for (int col = 0; col < 8; col++) {
                if (grid[row][col] == null) {
                    System.out.print("   |");
                } else {
                    String symbol = grid[row][col].toString().substring(0, 2); // e.g., "Wh", "Bl"
                    System.out.print(" " + symbol + "|");
                }
            }
            System.out.println(" " + (8 - row));
            System.out.println("  +---+---+---+---+---+---+---+---+");
        }
        System.out.println("    A   B   C   D   E   F   G   H");
    }

    /**
     * Returns the entire 2D grid of pieces.
     */
    public Piece[][] getGrid() {
        return grid;
    }
}
