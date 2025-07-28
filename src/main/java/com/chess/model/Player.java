package com.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the chess game.
 * Each player has a color and a list of their active pieces on the board.
 */
public class Player {
    private String color;              // Player color: "WHITE" or "BLACK"
    private List<Piece> pieces;       // List of all pieces currently owned by the player

    // Constructor: initializes the player with a color and an empty list of pieces
    public Player(String color) {
        this.color = color;
        this.pieces = new ArrayList<>();
    }

    // Returns the color of the player
    public String getColor() {
        return color;
    }

    // Returns the list of active pieces the player currently owns
    public List<Piece> getPieces() {
        return pieces;
    }

    // Adds a new piece to the player's list (usually at game start or when undoing a capture)
    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    // Removes a captured piece from the player's list
    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }

    /**
     * Checks whether the player still has their King.
     * This helps detect checkmate (game over when King is missing).
     */
    public boolean hasKing() {
        for (Piece p : pieces) {
            if (p instanceof King) {
                return true;
            }
        }
        return false;
    }
}

