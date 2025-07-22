package com.chess.controller;

import com.chess.model.Match;
import com.chess.model.Piece;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chess")
public class ChessController {

    private final Match match = new Match();

    /**
     * Returns the current state of the chess board.
     */
    @GetMapping("/board")
    public Piece[][] getBoard() {
        return match.getBoard().getGrid();
    }

    /**
     * Processes a move request from the frontend.
     */
    @PostMapping("/move")
    public String movePiece(@RequestParam int startRow,
                            @RequestParam int startCol,
                            @RequestParam int endRow,
                            @RequestParam int endCol) {

        boolean moveSuccess = match.move(startRow, startCol, endRow, endCol);
        if (moveSuccess) {
            return "Move successful. It's now " + match.getCurrentPlayer().getColor() + "'s turn.";
        } else {
            return "Invalid move.";
        }
    }

    /**
     * Returns which player's turn it is.
     */
    @GetMapping("/turn")
    public String getCurrentTurn() {
        return match.getCurrentPlayer().getColor();
    }
}
