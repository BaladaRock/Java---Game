package controller;

import model.ChessGameModel;
import model.ChessPiece;
import model.ChessSquare;
import view.ChessGameView;
import view.SquareView;

public class ChessGameController {
    private final ChessGameModel _chessGameModel;
    private final ChessGameView _chessBoardView;

    private boolean _moveWasPerformed = false;

    public ChessGameController(ChessGameModel chessGameModel, ChessGameView chessBoardView) {
        _chessGameModel = chessGameModel;
        _chessBoardView = chessBoardView;
        initialize();
    }

    private void initialize() {
        _chessGameModel.initializeChessBoard();
        _chessGameModel.initializePieces();
        _chessGameModel.initializePlayers();
    }

    public void handleSquareClick(int row, int column) {
        SquareView square = _chessBoardView.getSquare(row, column);
        String piece = square.getPiece();

        // Test the connection with the view via the click event
        System.out.println("Controller: Square clicked at: (" + row + ", " + column + ") with piece: " + piece);

        // to do: the rest of the logic, which manipulates the models
        _chessGameModel.setLastClickedSquare(row, column);

        ChessSquare chessSquare = _chessGameModel.getLastClickedSquare();
        ChessPiece pieceToMove = chessSquare.getPiece();

        if (pieceToMove != null && _chessGameModel.canApplyMove(pieceToMove, row, column)){
            _chessGameModel.applyMove(pieceToMove, row, column);
            _chessGameModel.emptyLastClickedSquare();
            _moveWasPerformed = true;
        }

        else {
            _moveWasPerformed = false;
        }

    }
}