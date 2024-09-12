package controller;

import model.*;
import view.ChessGameView;

import java.util.ArrayList;
import java.util.List;

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
        var square = _chessBoardView.getSquare(row, column);

        // Test the connection with the view via the click event
        var piece = square.getPiece();
        System.out.println("Controller: Square clicked at: (" + row + ", " + column + ") with piece: " + piece);

        // highlight possible moves logic
        var currentPiece = _chessGameModel.getPiece(row, column);

        if (currentPiece != null) {
            availableMoves = initializePossibleMovesHighlight(currentPiece, row, column);
        }

        var lastClicked = _chessGameModel.getPreviouslyClickedSquare();
        if (lastClicked == null) {
            _chessGameModel.setPreviouslyClickedSquare(row, column);
        } else {

            // Move piece logic
            var pieceToMove = lastClicked.get_piece();
            if (pieceToMove != null &&
                    _chessGameModel.canApplyMove(pieceToMove, lastClicked.get_row(), lastClicked.get_col(), row, column)
            ) {
                _moveWasPerformed = true;
                _chessGameModel.applyMove(pieceToMove, row, column);

                initiateMoveEffect(_chessGameModel.getPreviouslyClickedSquare(), row, column);

                _chessGameModel.emptyLastClickedSquare();
                _chessBoardView.resetHighlightedSquares(availableMoves);

            } else {
                _chessGameModel.setPreviouslyClickedSquare(row, column);
                _moveWasPerformed = false;
            }

        }

//        _chessBoardView.resetHighlightedSquares(availableMoves);

    }

    private Iterable<int[]> initializePossibleMovesHighlight(ChessPiece piece, int row, int col) {
        Iterable<Position> possibleMoves = _chessGameModel.getAvailableMoves(piece, row, col);

        List<int[]> moveCoordinates = new ArrayList<>();
        for (Position move : possibleMoves) {
            moveCoordinates.add(new int[]{move.x(), move.y()});
        }
        _chessBoardView.highlightPossibleMoves(moveCoordinates);

        return  moveCoordinates;
    }

    private void initiateMoveEffect(ChessSquare lastClickedSquare, int row, int column) {
        _chessBoardView.performMoveEffect(lastClickedSquare.get_positionCoordinates().x(),
                lastClickedSquare.get_positionCoordinates().y(),
                row,
                column
        );
    }
}