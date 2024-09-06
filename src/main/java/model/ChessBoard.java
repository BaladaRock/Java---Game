package model;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    public Iterable<Iterable<Position>> getAvailablePositions(ChessPiece pieceToMove, int rowIndex, int columnIndex) {
        Iterable<Iterable<Position>> rawMoves = pieceToMove.getAvailableMoves(rowIndex, columnIndex);

        return StreamSupport.stream(rawMoves.spliterator(), false)
                .map(this::filterValidPositions)
                .collect(Collectors.toList());
    }

    private Iterable<Position> filterValidPositions(Iterable<Position> positions) {
        return StreamSupport.stream(positions.spliterator(), false)
                .filter(this::isValidPosition)
                .collect(Collectors.toList());
    }

    private boolean isValidPosition(Position position) {
        return position.x() >= 0 && position.x() < size &&
                position.y() >= 0 && position.y() < size;
    }
}
