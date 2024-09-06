package model;

public class ChessBoard {

    private final int size;
    private ChessSquare[][] _squares;

    public ChessBoard(int size) {
        this.size = size;
    }

    public void initializePieces() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {

            }
        }
    }

    public ChessSquare getSquare(int row, int column) {
        return this._squares[row][column];
    }

    public void setPiece(ChessSquare square, ChessPiece pieceToMove) {
        square.setPiece(pieceToMove);
    }
}
