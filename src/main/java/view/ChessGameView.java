package view;

import controller.ChessGameController;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class ChessGameView extends GridPane {
    private final int SIZE;
    private final int TILE_SIZE = 60;
    private final SquareView[][] squares;
    private ChessGameController controller;

    public ChessGameView(int boardSize) {
        SIZE = boardSize;
        squares = new SquareView[SIZE][SIZE];

        setPadding(new Insets(10));
        setHgap(0);
        setVgap(0);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                SquareView square = new SquareView(row, col, TILE_SIZE, this);
                square.setPiece("test");
                squares[row][col] = square;
                add(square, col, row);
            }
        }
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
