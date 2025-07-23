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

    private final List<Move> moveHistory = new ArrayList<>();

    public Match() {
        reset();
    }

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
        if (gameOver) {
            throw new MoveValidationException("La partie est terminée.");
        }

        Piece piece = board.getPiece(fromRow, fromCol);
        if (piece == null) {
            throw new MoveValidationException("Aucune pièce à cette position.");
        }

        if (!piece.getColor().equals(currentPlayer.getColor())) {
            throw new MoveValidationException("Ce n’est pas le tour du joueur " + piece.getColor() + ".");
        }

        if (!piece.isValidMove(toRow, toCol, board.getGrid())) {
            throw new MoveValidationException("Déplacement invalide pour la pièce " + piece.getType() + ".");
        }

        Piece target = board.getPiece(toRow, toCol);
        if (target != null && target.getColor().equals(currentPlayer.getColor())) {
            throw new MoveValidationException("Impossible de capturer votre propre pièce.");
        }

        // Enregistrement du mouvement
        Move move = new Move(fromRow, fromCol, toRow, toCol, piece, target);
        moveHistory.add(move);

        board.setPiece(toRow, toCol, piece);
        board.setPiece(fromRow, fromCol, null);
        piece.setPosition(toRow, toCol);

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

    public boolean undoLastMove() {
        if (moveHistory.isEmpty() || gameOver) return false;

        Move lastMove = moveHistory.remove(moveHistory.size() - 1);
        Piece movedPiece = board.getPiece(lastMove.getToRow(), lastMove.getToCol());

        board.setPiece(lastMove.getFromRow(), lastMove.getFromCol(), movedPiece);
        movedPiece.setPosition(lastMove.getFromRow(), lastMove.getFromCol());

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

    public Player getOpponent() {
        return currentPlayer == whitePlayer ? blackPlayer : whitePlayer;
    }

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
