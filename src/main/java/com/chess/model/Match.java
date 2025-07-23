package com.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;
    private boolean gameOver = false;
    private String winner = null;

    private List<Move> moveHistory = new ArrayList<>(); // ✅ historique des coups

    public Match() {
        this.board = new Board();
        this.whitePlayer = new Player("WHITE");
        this.blackPlayer = new Player("BLACK");
        this.currentPlayer = whitePlayer;
        initializePieces();
    }

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

    public boolean move(int fromRow, int fromCol, int toRow, int toCol) {
        if (gameOver) return false;

        Piece piece = board.getPiece(fromRow, fromCol);
        if (piece == null || !piece.getColor().equals(currentPlayer.getColor())) {
            return false;
        }

        if (!piece.isValidMove(toRow, toCol, board.getGrid())) {
            return false;
        }

        Piece target = board.getPiece(toRow, toCol);
        if (target != null) {
            if (target.getColor().equals(currentPlayer.getColor())) return false;
            getOpponent().removePiece(target);

            if (target instanceof King) {
                gameOver = true;
                winner = currentPlayer.getColor();
                System.out.println("Game Over! " + winner + " wins.");
            }
        }

        board.setPiece(toRow, toCol, piece);
        board.setPiece(fromRow, fromCol, null);
        piece.setPosition(toRow, toCol);

        // ✅ Ajouter le coup à l’historique
        moveHistory.add(new Move(fromRow, fromCol, toRow, toCol, piece, target));

        if (!gameOver) {
            currentPlayer = getOpponent();
        }

        return true;
    }

    public Player getOpponent() {
        return currentPlayer == whitePlayer ? blackPlayer : whitePlayer;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getWinner() {
        return winner;
    }

    // ✅ Accès à l’historique des coups
    public List<Move> getMoveHistory() {
        return moveHistory;
    }

    // ✅ (Optionnel) Annuler le dernier coup
    public boolean undoLastMove() {
        if (moveHistory.isEmpty()) return false;

        Move last = moveHistory.remove(moveHistory.size() - 1);
        Piece piece = board.getPiece(last.getToRow(), last.getToCol());

        board.setPiece(last.getFromRow(), last.getFromCol(), piece);
        piece.setPosition(last.getFromRow(), last.getFromCol());
        board.setPiece(last.getToRow(), last.getToCol(), null);

        if (last.getCapturedPiece() != null) {
            Piece captured = createPiece(last.getCapturedPiece(), last.getToRow(), last.getToCol(), getOpponent().getColor());
            board.setPiece(last.getToRow(), last.getToCol(), captured);
            getOpponent().addPiece(captured);
        }

        currentPlayer = getOpponent(); // revenir au joueur précédent
        return true;
    }
}
