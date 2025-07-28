package com.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a chess match.
 * Handles the board state, players, turn management, and move history.
 */
public class Match {
    private Board board; // The chess board
    private Player whitePlayer; // White side
    private Player blackPlayer; // Black side
    private Player currentPlayer; // Whose turn it is
    private boolean gameOver = false; // True if the game has ended
    private String winner = null; // Stores the winner color if game is over

    private final List<Move> moveHistory = new ArrayList<>(); // History of all valid moves

    // Constructor initializes a new game
    public Match() {
        reset();
    }

    // Resets the game to the starting position
    public void reset() {
        this.board = new Board();
        this.whitePlayer = new Player("WHITE");
        this.blackPlayer = new Player("BLACK");
        this.currentPlayer = whitePlayer;
        this.moveHistory.clear();
        this.gameOver = false;
        this.winner = null;
        initializePieces();
    }

    // Sets up all pieces on the board
    private void initializePieces() {
        for (int col = 0; col < 8; col++) {
            Pawn blackPawn = new Pawn(1, col, "BLACK");
            Pawn whitePawn = new Pawn(6, col, "WHITE");

            board.setPiece(1, col, blackPawn);
            board.setPiece(6, col, whitePawn);

            blackPlayer.addPiece(blackPawn);
            whitePlayer.addPiece(whitePawn);
        }

        String[] pieceOrder = {"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"};

        for (int col = 0; col < 8; col++) {
            Piece blackPiece = createPiece(pieceOrder[col], 0, col, "BLACK");
            Piece whitePiece = createPiece(pieceOrder[col], 7, col, "WHITE");

            board.setPiece(0, col, blackPiece);
            board.setPiece(7, col, whitePiece);

            blackPlayer.addPiece(blackPiece);
            whitePlayer.addPiece(whitePiece);
        }
    }

    // Creates a piece instance based on type
    private Piece createPiece(String type, int row, int col, String color) {
        switch (type) {
            case "Rook": return new Rook(row, col, color);
            case "Knight": return new Knight(row, col, color);
            case "Bishop": return new Bishop(row, col, color);
            case "Queen": return new Queen(row, col, color);
            case "King": return new King(row, col, color);
            default: throw new IllegalArgumentException("Unknown piece type: " + type);
        }
    }

    // Makes a move after validation
    public boolean move(int fromRow, int fromCol, int toRow, int toCol) {
        if (gameOver) {
            throw new MoveValidationException("The game is already over.");
        }

        Piece piece = board.getPiece(fromRow, fromCol);
        if (piece == null) {
            throw new MoveValidationException("No piece at the selected position.");
        }

        if (!piece.getColor().equals(currentPlayer.getColor())) {
            throw new MoveValidationException("It's not " + piece.getColor() + "'s turn.");
        }

        if (!piece.isValidMove(toRow, toCol, board.getGrid())) {
            throw new MoveValidationException("Invalid move for piece " + piece.getType() + ".");
        }

        Piece target = board.getPiece(toRow, toCol);
        if (target != null && target.getColor().equals(currentPlayer.getColor())) {
            throw new MoveValidationException("You cannot capture your own piece.");
        }

        // Record the move
        Move move = new Move(fromRow, fromCol, toRow, toCol, piece, target);
        moveHistory.add(move);

        // Update board and piece position
        board.setPiece(toRow, toCol, piece);
        board.setPiece(fromRow, fromCol, null);
        piece.setPosition(toRow, toCol);

        // If opponent's piece captured
        if (target != null) {
            getOpponent().removePiece(target);
            if (target instanceof King) {
                gameOver = true;
                winner = currentPlayer.getColor();
            }
        }

        if (!gameOver) {
            currentPlayer = getOpponent();
        }

        return true;
    }

    // Reverses the last move (undo feature)
    public boolean undoLastMove() {
        if (moveHistory.isEmpty() || gameOver) return false;

        Move lastMove = moveHistory.remove(moveHistory.size() - 1);
        Piece movedPiece = board.getPiece(lastMove.getToRow(), lastMove.getToCol());

        // Move the piece back
        board.setPiece(lastMove.getFromRow(), lastMove.getFromCol(), movedPiece);
        movedPiece.setPosition(lastMove.getFromRow(), lastMove.getFromCol());

        // Restore captured piece if any
        if (lastMove.getCapturedPiece() != null) {
            Piece restored = createPiece(
                lastMove.getCapturedPiece(),
                lastMove.getToRow(),
                lastMove.getToCol(),
                getOpponent().getColor()
            );
            board.setPiece(lastMove.getToRow(), lastMove.getToCol(), restored);
            getOpponent().addPiece(restored);
        } else {
            board.setPiece(lastMove.getToRow(), lastMove.getToCol(), null);
        }

        currentPlayer = getOpponent();
        gameOver = false;
        winner = null;
        return true;
    }

    // Returns the opponent of the current player
    public Player getOpponent() {
        return currentPlayer == whitePlayer ? blackPlayer : whitePlayer;
    }

    // Getters
    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getWinner() {
        return winner;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public List<Move> getMoveHistory() {
        return moveHistory;
    }
}

