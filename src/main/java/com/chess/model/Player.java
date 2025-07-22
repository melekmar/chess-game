package com.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the chess game.
 * Holds their color and list of active pieces.
 */
public class Player {
    private String color;
    private List<Piece> pieces;

    public Player(String color) {
        this.color = color;
        this.pieces = new ArrayList<>();
    }

    public String getColor() {
        return color;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    // Add a piece to the player's list
    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    // Remove a captured piece
    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }

    // Check if the player still has their King on the board
    public boolean hasKing() {
        for (Piece p : pieces) {
            if (p instanceof King) {
                return true;
            }
        }
        return false;
    }
}
