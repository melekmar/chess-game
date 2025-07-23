package com.chess.model;

public class Move {
    private final int fromRow, fromCol;
    private final int toRow, toCol;
    private final String pieceType;
    private final String color;
    private final String capturedPiece;

    public Move(int fromRow, int fromCol, int toRow, int toCol, Piece piece, Piece captured) {
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
        this.pieceType = piece.getType();
        this.color = piece.getColor();
        this.capturedPiece = captured != null ? captured.getType() : null;
    }

    public String getNotation() {
        String move = color + " " + pieceType + " from (" + fromRow + "," + fromCol + ") to (" + toRow + "," + toCol + ")";
        if (capturedPiece != null) {
            move += " capturing " + capturedPiece;
        }
        return move;
    }
}
