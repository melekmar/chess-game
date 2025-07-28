package com.chess.service;

import com.chess.model.Match;
import com.chess.model.Piece;

/**
 * Service class responsible for managing the Match instance.
 * Acts as a bridge between the controller and the core game logic.
 */
public class GameService {
    private Match match; // Holds the current match instance

    // Constructor: initializes a new chess match
    public GameService() {
        this.match = new Match();
    }

    /**
     * Tries to make a move on the board from a starting position to a target position.
     * @return true if the move was successful, false otherwise.
     */
    public boolean move(int fromRow, int fromCol, int toRow, int toCol) {
        return match.move(fromRow, fromCol, toRow, toCol);
    }

    /**
     * Returns the current state of the board as a 2D array of pieces.
     */
    public Piece[][] getBoardState() {
        return match.getBoard().getGrid();
    }

    /**
     * Returns the color of the player whose turn it is.
     */
    public String getCurrentPlayer() {
        return match.getCurrentPlayer().getColor();
    }

    /**
     * Resets the game by initializing a new match.
     */
    public void resetMatch() {
        this.match = new Match();
    }
}

