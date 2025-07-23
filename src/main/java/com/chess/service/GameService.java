package com.chess.service;

import com.chess.model.Match;
import com.chess.model.Piece;

public class GameService {
    private Match match;

    public GameService() {
        this.match = new Match();
    }

    public boolean move(int fromRow, int fromCol, int toRow, int toCol) {
        return match.move(fromRow, fromCol, toRow, toCol);
    }

    public Piece[][] getBoardState() {
        return match.getBoard().getGrid();
    }

    public String getCurrentPlayer() {
        return match.getCurrentPlayer().getColor();
    }

    public void resetMatch() {
        this.match = new Match();
    }
}
