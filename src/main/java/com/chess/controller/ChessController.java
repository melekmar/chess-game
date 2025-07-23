package com.chess.controller;

import com.chess.model.Match;
import com.chess.model.Piece;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/chess")
public class ChessController {

    private Match match = new Match(); // instance non finalisée pour pouvoir reset

    @GetMapping("/board")
    public Piece[][] getBoard() {
        return match.getBoard().getGrid();
    }

    @PostMapping("/move")
    public Map<String, Object> movePiece(@RequestParam int fromRow, @RequestParam int fromCol,
                                         @RequestParam int toRow, @RequestParam int toCol) {
        Map<String, Object> response = new HashMap<>();

        try {
            boolean success = match.move(fromRow, fromCol, toRow, toCol);
            if (success) {
                response.put("status", "success");
                response.put("message", "Move completed successfully.");
                response.put("nextPlayer", match.getCurrentPlayer().getColor());
            } else {
                response.put("status", "error");
                response.put("message", "Invalid move.");
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Unexpected server error: " + e.getMessage());
        }

        return response;
    }

    @GetMapping("/status")
    public Map<String, Object> getStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("currentPlayer", match.getCurrentPlayer().getColor());
        status.put("gameOver", match.isGameOver());
        status.put("winner", match.getWinner());
        status.put("whiteRemaining", match.getWhitePlayer().getPieces().size());
        status.put("blackRemaining", match.getBlackPlayer().getPieces().size());
        return status;
    }

    @PostMapping("/reset")
    public Map<String, Object> resetGame() {
        match = new Match(); // réinitialiser la partie
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Game has been reset.");
        return response;
    }
}

