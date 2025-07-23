package com.chess.controller;

import com.chess.model.Match;
import com.chess.model.Piece;
import com.chess.model.Player;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/chess")
public class ChessController {

    private final Match match = new Match();

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

                if (match.isGameOver()) {
                    Player winner = match.getWinner();
                    response.put("gameOver", true);
                    response.put("winner", winner != null ? winner.getColor() : "None");
                }

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
        status.put("gameOver", match.isGameOver());
        status.put("currentPlayer", match.getCurrentPlayer().getColor());
        status.put("winner", match.getWinner() != null ? match.getWinner().getColor() : null);
        return status;
    }
}



