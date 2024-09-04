package controller;

import model.ChessGameModel;
import view.ChessGameView;

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
}