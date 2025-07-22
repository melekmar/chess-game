package com.chess.service;

import com.chess.model.Board;
import com.chess.model.Piece;

/**
 * Service layer for managing the chess game logic.
 */
public class GameService {
    private Board board;

    // Constructor initializes a new board
    public GameService() {
        this.board = new Board();
    }

    /**
     * Moves a piece from (fromRow, fromCol) to (toRow, toCol)
     * after checking if the move is valid.
     */
    public String move(int fromRow, int fromCol, int toRow, int toCol) {
        Piece piece = board.getPiece(fromRow, fromCol);

        if (piece == null) {
            return "No piece found at source position.";
        }

        // Check if move is valid based on the piece logic
        if (!piece.isValidMove(toRow, toCol, board.getGrid())) {
            return "Invalid move for the selected piece.";
        }

        // Move the piece
        board.movePiece(fromRow, fromCol, toRow, toCol);
        return "Moved " + piece.getClass().getSimpleName() +
               " from " + (8 - fromRow) + (char)('A' + fromCol) +
               " to " + (8 - toRow) + (char)('A' + toCol);
    }

    /**
     * Returns the board as a 2D array of Pieces.
     */
    public Piece[][] getBoardState() {
        return board.getGrid();
    }

    /**
     * Displays the board in the console.
     */
    public void printBoard() {
        board.printBoard();
    }
}
