package com.chess.model;

/**
 * Represents a move made in the chess game.
 * It keeps track of starting and ending positions, piece type, and any captured piece.
 */
public class Move {
    private final int fromRow, fromCol;         // Original position of the piece
    private final int toRow, toCol;             // New position after the move
    private final String pieceType;             // Type of the piece moved (e.g., "Pawn", "Knight")
    private final String color;                 // Color of the piece moved
    private final String capturedPiece;         // Type of piece captured (if any)

    // Constructor to initialize a move
    public Move(int fromRow, int fromCol, int toRow, int toCol, Piece piece, Piece captured) {
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
        this.pieceType = piece.getType();                    // Get the type of the piece being moved
        this.color = piece.getColor();                       // Get the color of the piece
        this.capturedPiece = captured != null ? captured.getType() : null; // Get captured piece type if any
    }

    // Getters for the position and metadata
    public int getFromRow() {
        return fromRow;
    }

    public int getFromCol() {
        return fromCol;
    }

    public int getToRow() {
        return toRow;
    }

    public int getToCol() {
        return toCol;
    }

    public String getPieceType() {
        return pieceType;
    }

    public String getColor() {
        return color;
    }

    public String getCapturedPiece() {
        return capturedPiece;
    }

    // Returns a readable description of the move
    public String getNotation() {
        String move = color + " " + pieceType + " from (" + fromRow + "," + fromCol + ") to (" + toRow + "," + toCol + ")";
        if (capturedPiece != null) {
            move += " capturing " + capturedPiece;
        }
        return move;
    }
}


