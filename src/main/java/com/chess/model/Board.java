package com.chess.model;

/**
 * Represents the 8x8 chess board.
 * Manages piece placement, retrieval, and board state.
 */
public class Board {
    private Piece[][] grid; // 2D array representing the chess board

    public Board() {
        grid = new Piece[8][8]; // Empty grid, pieces added by Match
    }

    public Piece getPiece(int row, int col) {
        return grid[row][col];
    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        Piece moving = grid[fromRow][fromCol];
        if (moving != null) {
            moving.setPosition(toRow, toCol);
            grid[toRow][toCol] = moving;
            grid[fromRow][fromCol] = null;
        }
    }

    public void setPiece(int row, int col, Piece piece) {
        grid[row][col] = piece;
    }

    public Piece[][] getGrid() {
        return grid;
    }

    public void printBoard() {
        System.out.println("    A   B   C   D   E   F   G   H");
        System.out.println("  +---+---+---+---+---+---+---+---+");
        for (int row = 0; row < 8; row++) {
            System.out.print((8 - row) + " |");
            for (int col = 0; col < 8; col++) {
                if (grid[row][col] == null) {
                    System.out.print("   |");
                } else {
                    String symbol = grid[row][col].toString().substring(0, 2);
                    System.out.print(" " + symbol + "|");
                }
            }
            System.out.println(" " + (8 - row));
            System.out.println("  +---+---+---+---+---+---+---+---+");
        }
        System.out.println("    A   B   C   D   E   F   G   H");
    }
}
