package model;

import java.util.Objects;

public class ChessBoard {

    private final int size;
    private final ChessSquare[][] _squares;

    public ChessBoard(int size) {
        this.size = size;
        _squares = new ChessSquare[size][size];
        initializeSquares();
    }

    private void initializeSquares() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                Color color = (row + column) % 2 == 0 ? Color.white : Color.black;
                Objects.requireNonNull(this._squares)[row][column] = new ChessSquare(row, column, color);
            }
        }
    }

    public ChessSquare getSquare(int row, int column) {
        return this._squares[row][column];
    }

    public void setPiece(ChessSquare square, ChessPiece pieceToMove) {
        square.set_piece(pieceToMove);
    }
}
