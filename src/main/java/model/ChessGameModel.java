package model;

import java.util.ArrayList;
import java.util.List;

public class ChessGameModel {
    private final int _boardSize;
    private ChessBoard _chessBoard;
    private ChessSquare _lastClickedSquare;
    private ChessPlayer _firstPlayer;
    private ChessPlayer _secondPlayer;
    private ChessPiece _activePiece;

    public ChessGameModel(int boardSize) {
        _boardSize = boardSize;
    }

    public void initializeChessBoard() {
        this._chessBoard = new ChessBoard(_boardSize);
    }

    public void initializePlayers() {
        this._firstPlayer = new ChessPlayer(Color.white);
        this._secondPlayer = new ChessPlayer(Color.white);
    }

    public void setInitialPieces(Iterable<PiecePlacement> piecePlacements) {
        for (PiecePlacement placement : piecePlacements) {
            ChessPiece piece = placement.piece();
            int row = placement.row();
            int col = placement.column();
            piece.columnStartingPosition = row;
            piece.rowStartingPosition = col;

            _chessBoard.getSquare(row, col).set_piece(piece);
        }
    }

    public ChessSquare getPreviouslyClickedSquare() {
        return this._lastClickedSquare;
    }

    public void setPreviouslyClickedSquare(int row, int column) {
        _lastClickedSquare = _chessBoard.getSquare(row, column);
    }

    public void emptyLastClickedSquare() {
        this._lastClickedSquare = null;
    }

    public void applyMove(ChessPiece pieceToMove, int row, int column) {
        _chessBoard.setPiece(_chessBoard.getSquare(row, column), pieceToMove);
        _chessBoard.setPiece(_chessBoard.getSquare(
                _lastClickedSquare.get_row(),
                _lastClickedSquare.get_col()
        ), null);
        System.out.println("Model: Applied move at: (" + row + ", " + column + ") with piece: " + pieceToMove.getName());
    }

    public boolean canApplyMove(
            ChessPiece pieceToMove,
            int startRow,
            int startCol,
            int targetRow,
            int targetCol
    ) {
        if (pieceToMove == null) {
            return false;
        }

        var availablePositions = _chessBoard.getAvailablePositions(pieceToMove, startRow, startCol);
        for (Iterable<Position> direction : availablePositions) {
            List<Position> validMovesInDirection = new ArrayList<>();
            for (Position pos : direction) {
                var square = _chessBoard.getSquare(pos.x(), pos.y());
                var pieceOnSquare = square.get_piece();

                // Same piece color is found
                if (pieceOnSquare != null && pieceOnSquare.getColor() == pieceToMove.getColor()) {
                    break;
                }

                validMovesInDirection.add(pos);

                // Enemy piece is found
                if (pieceOnSquare != null && pieceOnSquare.getColor() != pieceToMove.getColor()) {
                    break;
                }
            }

            for (Position validPos : validMovesInDirection) {
                if (validPos.x() == targetRow && validPos.y() == targetCol) {
                    return true;
                }
            }
        }

        return false;
    }

    public Iterable<int[]> getAvailableMoves(ChessPiece pieceToMove, int startRow, int startCol) {
        List<int[]> validMoves = new ArrayList<>();

        var availablePositions = _chessBoard.getAvailablePositions(pieceToMove, startRow, startCol);
        for (Iterable<Position> direction : availablePositions) {
            for (Position pos : direction) {
                var square = _chessBoard.getSquare(pos.x(), pos.y());
                var pieceOnSquare = square.get_piece();

                if (pieceOnSquare != null && pieceOnSquare.getColor() == pieceToMove.getColor()) {
                    break;
                }

                validMoves.add(new int[]{pos.x(), pos.y()});

                if (pieceOnSquare != null && pieceOnSquare.getColor() != pieceToMove.getColor()) {
                    break;
                }
            }
        }

        return validMoves;
    }

    public ChessPiece getPiece(int row, int column) {
        return _chessBoard.getSquare(row, column).get_piece();
    }

    public void updateBoardState(ChessPiece currentPiece, int row, int column) {
        _activePiece = currentPiece;
        _lastClickedSquare = _chessBoard.getSquare(row, column);
    }

    public boolean checkIsMoveAndApplyMove(int row, int column) {
        var moveIsPossible = _activePiece != null &&
                canApplyMove(_activePiece,
                        _lastClickedSquare.get_row(),
                        _lastClickedSquare.get_col(),
                        row,
                        column
                );

        if (moveIsPossible) {
            applyMove(_activePiece, row, column);
        }

        return moveIsPossible;
    }

    public ChessPiece get_activePiece() {
        return _activePiece;
    }

    public boolean checkCapture() {
        return true;
    }
}
