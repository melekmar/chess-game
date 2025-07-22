package com.chess.model;

public class Match {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;

    public Match() {
        this.board = new Board(); // Initialize the 8x8 board
        this.whitePlayer = new Player("WHITE");
        this.blackPlayer = new Player("BLACK");
        this.currentPlayer = whitePlayer; // White starts
        initializePieces(); // Set up all pieces on the board
    }

    /**
     * Initializes the chess board with standard piece placement.
     */
    private void initializePieces() {
        // Place pawns
        for (int col = 0; col < 8; col++) {
            Pawn blackPawn = new Pawn(1, col, "BLACK");
            Pawn whitePawn = new Pawn(6, col, "WHITE");

            board.setPiece(1, col, blackPawn);
            board.setPiece(6, col, whitePawn);

            blackPlayer.addPiece(blackPawn);
            whitePlayer.addPiece(whitePawn);
        }

        // Place major pieces using ordered array
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

    /**
     * Factory method to create pieces based on type.
     */
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

    /**
     * Attempts to move a piece and switch turns.
     */
    public boolean move(int fromRow, int fromCol, int toRow, int toCol) {
        Piece piece = board.getPiece(fromRow, fromCol);
        if (piece == null || !piece.getColor().equals(currentPlayer.getColor())) {
            return false; // Invalid piece or wrong player's turn
        }

        if (!piece.isValidMove(toRow, toCol, board.getGrid())) {
            return false; // Invalid movement logic
        }

        // Handle capture
        Piece target = board.getPiece(toRow, toCol);
        if (target != null) {
            if (target.getColor().equals(currentPlayer.getColor())) {
                return false; // Can't capture own piece
            }
            getOpponent().removePiece(target);
        }

        // Apply move
        board.setPiece(toRow, toCol, piece);
        board.setPiece(fromRow, fromCol, null);
        piece.setPosition(toRow, toCol);

        // Switch player turn
        currentPlayer = getOpponent();
        return true;
    }

    /**
     * Returns the opponent of the current player.
     */
    public Player getOpponent() {
        return currentPlayer == whitePlayer ? blackPlayer : whitePlayer;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}

