package controller;

import model.*;
import view.ChessGameView;
import view.SquareView;

import java.util.ArrayList;
import java.util.List;

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
        List<PiecePlacement> initialPlacements = createInitialPiecePlacements();
        _chessGameModel.setInitialPieces(initialPlacements);
        _chessGameModel.initializePlayers();
    }

    private List<PiecePlacement> createInitialPiecePlacements() {
        List<PiecePlacement> placements = new ArrayList<>();
        int size = _chessBoardView.getBoardSize();

        for (int col = 0; col < size; col++) {
//            placements.add(new PiecePlacement(new Pawn("White Pawn", Color.white), size - 2, col));
//            placements.add(new PiecePlacement(new Pawn("Black Pawn", Color.black), size - 7, col));
        }

//        placements.add(new PiecePlacement(new Rook("White Rook", Color.white), size - 1, 0));
//        placements.add(new PiecePlacement(new Rook("White Rook", Color.white), size - 1, size - 1));
        placements.add(new PiecePlacement(new Knight("White Knight", Color.white), size - 1, 1));
        placements.add(new PiecePlacement(new Knight("White Knight", Color.white), size - 1, size - 2));
//        placements.add(new PiecePlacement(new Bishop("White Bishop", Color.white), size - 1, 2));
//        placements.add(new PiecePlacement(new Bishop("White Bishop", Color.white), size - 1, size - 3));
//        placements.add(new PiecePlacement(new Queen("White Queen", Color.white), size - 1, 3));
//        placements.add(new PiecePlacement(new King("White King", Color.white), size - 1, 4));

//        placements.add(new PiecePlacement(new Rook("Black Rook", Color.black), size - 8, 0));
//        placements.add(new PiecePlacement(new Rook("Black Rook", Color.black), size - 8, size - 1));
        placements.add(new PiecePlacement(new Knight("Black Knight", Color.black), size - 8, 1));
        placements.add(new PiecePlacement(new Knight("Black Knight", Color.black), size - 8, size - 2));
//        placements.add(new PiecePlacement(new Bishop("Black Bishop", Color.black), size - 8, 2));
//        placements.add(new PiecePlacement(new Bishop("Black Bishop", Color.black), size - 8, size - 3));
//        placements.add(new PiecePlacement(new Queen("Black Queen", Color.black), size - 8, 3));
//        placements.add(new PiecePlacement(new King("Black King", Color.black), size - 8, 4));

        return placements;
    }

    public void handleSquareClick(int row, int column) {
        SquareView square = _chessBoardView.getSquare(row, column);
        String piece = square.getPiece();

        // Test the connection with the view via the click event
        System.out.println("Controller: Square clicked at: (" + row + ", " + column + ") with piece: " + piece);

        ChessSquare lastClicked = _chessGameModel.getPreviouslyClickedSquare();
        if(lastClicked == null) {
            _chessGameModel.setPreviouslyClickedSquare(row, column);
            return;
        }

        ChessPiece pieceToMove = lastClicked.get_piece();

        if (pieceToMove != null && _chessGameModel.canApplyMove(pieceToMove, row, column)){
            _moveWasPerformed = true;
            _chessGameModel.applyMove(pieceToMove, row, column);

            initiateMoveEffect(_chessGameModel.getPreviouslyClickedSquare(), row, column);

            _chessGameModel.emptyLastClickedSquare();

        }

        else {
            _chessGameModel.setPreviouslyClickedSquare(row, column);
            _moveWasPerformed = false;
        }

    }

    private void initiateMoveEffect(ChessSquare lastClickedSquare, int row, int column) {
        _chessBoardView.performMoveEffect(lastClickedSquare.get_positionCoordinates().x(),
                lastClickedSquare.get_positionCoordinates().y(),
                row,
                column
        );
    }
}