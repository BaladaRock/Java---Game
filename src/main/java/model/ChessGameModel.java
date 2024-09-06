package model;

import java.util.ArrayList;
import java.util.List;

public class ChessGameModel {
    private final int _boardSize;
    private ChessBoard _chessBoard;
    private ChessSquare _lastClickedSquare;
    private ChessPlayer _firstPlayer;
    private ChessPlayer _secondPlayer;

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

    public void setInitialPieces(List<PiecePlacement> piecePlacements) {
        for (PiecePlacement placement : piecePlacements) {
            ChessPiece piece = placement.piece();
            int row = placement.row();
            int col = placement.column();
            _chessBoard.getSquare(row, col).set_piece(piece);
        }
    }

    public ChessSquare getPreviouslyClickedSquare() {
        return this._lastClickedSquare;
    }

    public void setPreviouslyClickedSquare(int row, int column)
    {
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
    }

    public boolean canApplyMove(
            ChessPiece pieceToMove,
            int startRow,
            int startCol,
            int targetRow,
            int targetCol
    ) {
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
}
