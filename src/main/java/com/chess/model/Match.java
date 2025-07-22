package com.chess.model;

/**
 * Manages the game state, player turns, and interactions between players and the board.
 */
public class Match {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;

    public Match() {
        this.board = new Board(); // Initialize the 8x8 board
        this.whitePlayer = new Player("WHITE"); // Create white player
        this.blackPlayer = new Player("BLACK"); // Create black player
        this.currentPlayer = whitePlayer; // White starts
        initializePieces(); // Set up all pieces on the board
    }

    /**
     * Initialize the standard chess setup for both players.
     */
    private void initializePieces() {
        // Initialize pawns
        for (int col = 0; col < 8; col++) {
            board.setPiece(1, col, new Pawn(1, col, "BLACK"));
            board.setPiece(6, col, new Pawn(6, col, "WHITE"));
            blackPlayer.addPiece(board.getPiece(1, col));
            whitePlayer.addPiece(board.getPiece(6, col));
        }

        // Place the other major pieces
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
     * Factory method to create specific pieces by name.
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
            return false; // Invalid piece or wrong player
        }

        if (!piece.isValidMove(toRow, toCol, board.getGrid())) {
            return false; // Invalid movement
        }

        // Capture if enemy piece is present
        Piece target = board.getPiece(toRow, toCol);
        if (target != null) {
            if (target.getColor().equals(currentPlayer.getColor())) return false;
            getOpponent().removePiece(target);
        }

        // Move piece
        board.setPiece(toRow, toCol, piece);
        board.setPiece(fromRow, fromCol, null);
        piece.setPosition(toRow, toCol);

        // Switch player
        currentPlayer = getOpponent();
        return true;
    }

    /**
     * Returns the opponent of the current player.
     */
    public Player getOpponent() {
        return (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
