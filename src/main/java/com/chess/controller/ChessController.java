package com.chess.controller;

import com.chess.model.Board;
import com.chess.model.Piece;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for chess game logic.
 */
@RestController
@RequestMapping("/api/chess")
public class ChessController {

    private final Board board = new Board();

    /**
     * Get the current state of the board.
     */
    @GetMapping("/board")
    public Piece[][] getBoard() {
        return board.getGrid();
    }

    /**
     * Move a piece from one cell to another.
     */
    @PostMapping("/move")
    public String movePiece(@RequestParam int startX, @RequestParam int startY,
                            @RequestParam int endX, @RequestParam int endY) {

        Piece piece = board.getPiece(startX, startY);
        if (piece == null) return "No piece at the source location.";

        if (piece.isValidMove(endX, endY, board.getGrid())) {
            board.movePiece(startX, startY, endX, endY);
            return "Move successful.";
        }

        return "Invalid move.";
    }
}
