package view;

import controller.ChessGameController;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class ChessGameView extends GridPane {
    private final int SIZE;
    private final int TILE_SIZE = 75;
    private final SquareView[][] squares;
    private ChessGameController controller;
    private Iterable<int[]> highlightedSquares;

    public ChessGameView(int boardSize) {
        SIZE = boardSize;
        squares = new SquareView[SIZE][SIZE];
        setPadding(new Insets(30));
        setHgap(0);
        setVgap(0);

        drawBoard();
    }

    private void drawBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                SquareView square = new SquareView(row, col, TILE_SIZE, this);
                square.setPiece(getInitialPiece(row, col));
                squares[row][col] = square;
                add(square, col, row);
            }
        }
    }

    private String getInitialPiece(int row, int col) {
        if (row == 1) {
            return "black_pawn";
        } else if (row == 6) {
            return "white_pawn";
        } else if (row == 0) {
            return switch (col) {
                case 0, 7 -> "black_rook";
                case 1, 6 -> "black_knight";
                case 2, 5 -> "black_bishop";
                case 3 -> "black_queen";
                case 4 -> "black_king";
                default -> "";
            };
        } else if (row == 7) {
            return switch (col) {
                case 0, 7 -> "white_rook";
                case 1, 6 -> "white_knight";
                case 2, 5 -> "white_bishop";
                case 3 -> "white_queen";
                case 4 -> "white_king";
                default -> "";
            };
        }
        return "";
    }

    public void setController(ChessGameController controller) {
        this.controller = controller;
    }

    public void handleSquareClick(SquareView square) {
        if (controller == null) {
            return;
        }
        controller.handleSquareClick(square.getRow(), square.getCol());

        // Test the connection with the controller via the click event
        System.out.println("View: Square clicked at: (" + square.getRow() + ", " + square.getCol()
                + ") with piece: " + square.getPiece());
    }

    public SquareView getSquare(int row, int col) {
        return squares[row][col];
    }

    public int getBoardSize() {
        return SIZE;
    }

    public void performMoveEffect(int oldX, int oldY, int newX, int newY) {
        var piece = squares[oldX][oldY].getPiece();
        squares[newX][newY].setPiece(piece);
        squares[oldX][oldY].setPiece(null);
    }

    public void highlightPossibleMoves(Iterable<int[]> possibleMovesCoordinates) {
        for (int[] coordinates : possibleMovesCoordinates) {
            int row = coordinates[0];
            int col = coordinates[1];

            squares[row][col].highlightSquare();
        }

        highlightedSquares = possibleMovesCoordinates;
    }

    public void resetHighlightedSquares(Iterable<int[]> availableMoves) {
        for (int[] coordinates : availableMoves) {
            int row = coordinates[0];
            int col = coordinates[1];

            squares[row][col].resetHighlightedSquare();
        }
        
        highlightedSquares = null;
    }

    public void handlePieceDrop(SquareView startSquare, double mouseX, double mouseY) {
        int targetRow = (int)Math.round(mouseY / TILE_SIZE);
        int targetCol = (int)Math.round(mouseX / TILE_SIZE);
        if (targetRow < 0 || targetRow >= SIZE || targetCol < 0 || targetCol >= SIZE) {
            return;
        }

        SquareView targetSquare = getSquare(targetRow, targetCol);
        handleSquareClick(targetSquare);

        System.out.println("Moved piece from (" + startSquare.getRow() + ", " + startSquare.getCol() + ") "
                + "to (" + targetRow + ", " + targetCol + ")");
    }
}
