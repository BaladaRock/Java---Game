package view;

import controller.ChessGameController;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class ChessGameView extends GridPane {
    private final int SIZE;
    private final SquareView[][] squares;
    private ChessGameController controller;

    public ChessGameView(int boardSize) {
        SIZE = boardSize;
        squares = new SquareView[SIZE][SIZE];
        setPadding(new Insets(10));
        setHgap(0);
        setVgap(0);

        drawBoard();
    }

    private void drawBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int TILE_SIZE = 60;
                SquareView square = new SquareView(row, col, TILE_SIZE, this);
                square.setPiece(getInitialPiece(row, col));
                squares[row][col] = square;
                add(square, col, row);
            }
        }
    }

    private String getInitialPiece(int row, int col) {
        if (row == 1) {
            return "white_pawn";
        } else if (row == 6) {
            return "black_pawn";
        } else if (row == 0) {
            return switch (col) {
                case 0, 7 -> "white_rook";
                case 1, 6 -> "white_knight";
                case 2, 5 -> "white_bishop";
                case 3 -> "white_queen";
                case 4 -> "white_king";
                default -> "";
            };
        } else if (row == 7) {
            return switch (col) {
                case 0, 7 -> "black_rook";
                case 1, 6 -> "black_knight";
                case 2, 5 -> "black_bishop";
                case 3 -> "black_queen";
                case 4 -> "black_king";
                default -> "";
            };
        }
        return "";
    }

    public void setController(ChessGameController controller) {
        this.controller = controller;
    }

    public void handleSquareClick(SquareView square) {
        if (controller != null) {
            controller.handleSquareClick(square.getRow(), square.getCol());

            // Test the connection with the controller via the click event
            System.out.println("View: Square clicked at: (" + square.getRow() + ", " + square.getCol()
                    + ") with piece: " + square.getPiece());
        }
    }

    public SquareView getSquare(int row, int col) {
        return squares[row][col];
    }
}
