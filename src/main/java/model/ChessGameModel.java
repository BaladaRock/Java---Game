package model;

public class ChessGameModel {
    private int _boardSize;
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

    public void initializePieces() {
        this._chessBoard.initializePieces();
    }

    public ChessSquare getLastClickedSquare() {
        return this._lastClickedSquare;
    }

    public void setLastClickedSquare(int row, int column)
    {
        _lastClickedSquare = _chessBoard.getSquare(row, column);
    }

    public void emptyLastClickedSquare() {
        this._lastClickedSquare = null;
    }

    public void applyMove(ChessPiece pieceToMove, int row, int column) {
        _chessBoard.setPiece(_chessBoard.getSquare(row, column), pieceToMove);
        _chessBoard.setPiece(_chessBoard.getSquare(
                _lastClickedSquare.getRow(),
                _lastClickedSquare.getCol()
        ), null);
    }

    public boolean canApplyMove(ChessPiece pieceToMove, int row, int column) {
        return true;
    }
}
