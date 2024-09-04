package controller;

import model.ChessGameModel;
import view.ChessGameView;
import view.SquareView;

public class ChessGameController {
    private final ChessGameModel _chessGameModel;
    private final ChessGameView _chessBoardView;

    public ChessGameController(ChessGameModel chessGameModel, ChessGameView chessBoardView) {
        _chessGameModel = chessGameModel;
        _chessBoardView = chessBoardView;
        initialize();
    }

    private void initialize() {
        // to do : add the controller logic, including:
        // click event logic
        // moves management
        // UI update
        // model initialization
    }

    public void handleSquareClick(int row, int column) {
        SquareView square = _chessBoardView.getSquare(row, column);
        String piece = square.getPiece();

        // Test the connection with the view via the click event
        System.out.println("Controller: Square clicked at: (" + row + ", " + column + ") with piece: " + piece);

        // to do: the rest of the logic, which manipulates the models
    }
}