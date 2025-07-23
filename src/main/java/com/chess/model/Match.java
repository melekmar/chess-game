package com.chess.model;

public class Match {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;
    private boolean gameOver = false;
    private Player winner = null; // Change from String to Player

    public Match() {
        this.board = new Board(); // Initialize the 8x8 board
        this.whitePlayer = new Player("WHITE");
        this.blackPlayer = new Player("BLACK");
        this.currentPlayer = whitePlayer; // White starts
        initializePieces(); // Set up all pieces on the board
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
                winner = currentPlayer;
                System.out.println("Game Over! " + winner.getColor() + " wins.");
            }
        }

        board.setPiece(toRow, toCol, piece);
        board.setPiece(fromRow, fromCol, null);
        piece.setPosition(toRow, toCol);

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

    public boolean isGameOver() {
        return gameOver;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }
}
