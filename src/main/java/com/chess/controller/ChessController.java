package com.chess.controller;

import com.chess.model.Match;
import com.chess.model.Move;
import com.chess.model.Piece;
import com.chess.model.MoveValidationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    public Map<String, Object> movePiece(@RequestParam("fromRow") int fromRow,
                                         @RequestParam("fromCol") int fromCol,
                                         @RequestParam("toRow") int toRow,
                                         @RequestParam("toCol") int toCol) {
        Map<String, Object> response = new HashMap<>();

        try {
            boolean success = match.move(fromRow, fromCol, toRow, toCol);
            if (success) {
                response.put("status", "success");
                response.put("message", "Move completed successfully.");
                response.put("nextPlayer", match.getCurrentPlayer().getColor());
                response.put("gameOver", match.isGameOver());
                response.put("winner", match.getWinner());
            } else {
                response.put("status", "error");
                response.put("message", "Invalid move.");
            }
        } catch (MoveValidationException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Unexpected server error: " + e.getMessage());
        }

        return response;
    }

    @GetMapping("/history")
    public List<String> getMoveHistory() {
        return match.getMoveHistory().stream().map(Move::getNotation).toList();
    }

    @PostMapping("/reset")
    public Map<String, String> resetGame() {
        match.reset();
        Map<String, String> result = new HashMap<>();
        result.put("message", "Game reset successfully.");
        return result;
    }
}

