package controller;

import model.*;
import view.ChessGameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChessGameController {
    private final ChessGameModel _chessGameModel;
    private final ChessGameView _chessBoardView;

    private Iterable<int[]> availableMoves;
    private boolean _moveWasPerformed = false;

    public ChessGameController(ChessGameModel chessGameModel, ChessGameView chessBoardView) {
        _chessGameModel = chessGameModel;
        _chessBoardView = chessBoardView;
        initialize();
    }

    private void initialize() {
        _chessGameModel.initializeChessBoard();
        availableMoves = new ArrayList<>();
        _chessGameModel.setInitialPieces(createInitialPiecePlacements());
        _chessGameModel.initializePlayers();
    }

    private List<PiecePlacement> createInitialPiecePlacements() {
        List<PiecePlacement> placements = new ArrayList<>();
        int size = _chessBoardView.getBoardSize();

        for (int col = 0; col < size; col++) {
            placements.add(new PiecePlacement(new Pawn("White Pawn", Color.white), size - 2, col));
            placements.add(new PiecePlacement(new Pawn("Black Pawn", Color.black), size - 7, col));
        }

        placements.add(new PiecePlacement(new Rook("White Rook", Color.white), size - 1, 0));
        placements.add(new PiecePlacement(new Rook("White Rook", Color.white), size - 1, size - 1));
        placements.add(new PiecePlacement(new Knight("White Knight", Color.white), size - 1, 1));
        placements.add(new PiecePlacement(new Knight("White Knight", Color.white), size - 1, size - 2));
        placements.add(new PiecePlacement(new Bishop("White Bishop", Color.white), size - 1, 2));
        placements.add(new PiecePlacement(new Bishop("White Bishop", Color.white), size - 1, size - 3));
        placements.add(new PiecePlacement(new Queen("White Queen", Color.white), size - 1, 3));
        placements.add(new PiecePlacement(new King("White King", Color.white), size - 1, 4));

        placements.add(new PiecePlacement(new Rook("Black Rook", Color.black), size - 8, 0));
        placements.add(new PiecePlacement(new Rook("Black Rook", Color.black), size - 8, size - 1));
        placements.add(new PiecePlacement(new Knight("Black Knight", Color.black), size - 8, 1));
        placements.add(new PiecePlacement(new Knight("Black Knight", Color.black), size - 8, size - 2));
        placements.add(new PiecePlacement(new Bishop("Black Bishop", Color.black), size - 8, 2));
        placements.add(new PiecePlacement(new Bishop("Black Bishop", Color.black), size - 8, size - 3));
        placements.add(new PiecePlacement(new Queen("Black Queen", Color.black), size - 8, 3));
        placements.add(new PiecePlacement(new King("Black King", Color.black), size - 8, 4));

        return placements;
    }

    public void handleSquareClick(int row, int column) {
        // Test the connection with the view via click event
        var square = _chessBoardView.getSquare(row, column);
        var piece = square.getPiece();
        System.out.println("Controller: Square clicked at: (" + row + ", " + column + ") with piece: " + piece);

        Optional.ofNullable(availableMoves).ifPresent(_chessBoardView::resetHighlightedSquares);
        _moveWasPerformed = false;
        if (_chessGameModel.checkIsMoveAndApplyMove(row, column)) {
            _moveWasPerformed = true;
            initiateMoveEffect(_chessGameModel.getPreviouslyClickedSquare(), row, column);
            _chessGameModel.updateBoardState(null, row, column);
        } else {
            handlePieceSelection(row, column);
        }
    }

    private void handlePieceSelection(int row, int column) {
        var clickedPiece = _chessGameModel.getPiece(row, column);
        if (clickedPiece != null && _chessGameModel.get_activePiece() == null) {
            initiatePossibleMovesHighlight(clickedPiece, row, column);
            _chessGameModel.updateBoardState(clickedPiece, row, column);
        } else {
            _chessGameModel.updateBoardState(null, row, column);
        }
    }

    private void initiatePossibleMovesHighlight(ChessPiece piece, int row, int col) {
        var possibleMoves = _chessGameModel.getAvailableMoves(piece, row, col);
        if (possibleMoves == null) {
            return;
        }

        _chessBoardView.highlightPossibleMoves(possibleMoves);
        this.availableMoves = possibleMoves;
    }

    private void initiateMoveEffect(ChessSquare lastClickedSquare, int row, int column) {
        _chessBoardView.performMoveEffect(lastClickedSquare.get_positionCoordinates().x(),
                lastClickedSquare.get_positionCoordinates().y(),
                row,
                column
        );
    }
}