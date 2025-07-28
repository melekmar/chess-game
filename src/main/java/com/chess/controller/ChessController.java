package com.chess.controller;

import com.chess.model.Match;
import com.chess.model.Move;
import com.chess.model.Piece;
import com.chess.model.MoveValidationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST controller that exposes endpoints for interacting with the chess game.
 */
@RestController
@RequestMapping("/api/chess")
public class ChessController {

    // A single Match instance that holds the state of the game
    private final Match match = new Match();

    /**
     * GET endpoint to retrieve the current board state.
     * @return 2D array representing the board.
     */
    @GetMapping("/board")
    public Piece[][] getBoard() {
        return match.getBoard().getGrid();
    }

    /**
     * POST endpoint to move a piece from one position to another.
     * @param fromRow starting row index
     * @param fromCol starting column index
     * @param toRow destination row index
     * @param toCol destination column index
     * @return a response map with status and game info
     */
    @PostMapping("/move")
    public Map<String, Object> movePiece(@RequestParam("fromRow") int fromRow,
                                         @RequestParam("fromCol") int fromCol,
                                         @RequestParam("toRow") int toRow,
                                         @RequestParam("toCol") int toCol) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Attempt to perform the move
            boolean success = match.move(fromRow, fromCol, toRow, toCol);
            if (success) {
                // Move successful
                response.put("status", "success");
                response.put("message", "Move completed successfully.");
                response.put("nextPlayer", match.getCurrentPlayer().getColor());
                response.put("gameOver", match.isGameOver());
                response.put("winner", match.getWinner());
            } else {
                // Move failed without an exception
                response.put("status", "error");
                response.put("message", "Invalid move.");
            }
        } catch (MoveValidationException e) {
            // Invalid move due to game rules
            response.put("status", "error");
            response.put("message", e.getMessage());
        } catch (Exception e) {
            // Unexpected error occurred
            response.put("status", "error");
            response.put("message", "Unexpected server error: " + e.getMessage());
        }

        return response;
    }

    /**
     * GET endpoint to fetch the move history as human-readable strings.
     * @return list of move notations
     */
    @GetMapping("/history")
    public List<String> getMoveHistory() {
        return match.getMoveHistory().stream().map(Move::getNotation).toList();
    }

    /**
     * POST endpoint to reset the game to its initial state.
     * @return confirmation message
     */
    @PostMapping("/reset")
    public Map<String, String> resetGame() {
        match.reset();
        Map<String, String> result = new HashMap<>();
        result.put("message", "Game reset successfully.");
        return result;
    }
}
