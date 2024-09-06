package model;

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

    public boolean canApplyMove(ChessPiece pieceToMove, int row, int column) {
        return true;
    }
}
