package model;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {

    public Bishop(String name, Color color) {
        super(name, color);
    }

    @Override
    public Iterable<Iterable<Position>> getAvailableMoves(int rowIndex, int colIndex, int size) {
        this.rowStartingPosition = rowIndex;
        this.columnStartingPosition = colIndex;

        return List.of(
                getUpperLeftDiagonalMoves(rowIndex, colIndex, size),
                getUpperRightDiagonalMoves(rowIndex, colIndex, size),
                getLowerLeftDiagonalMoves(rowIndex, colIndex, size),
                getLowerRightDiagonalMoves(rowIndex, colIndex, size)
        );

    }

    private Iterable<Position> getUpperLeftDiagonalMoves(int rowPosition, int columnPosition, int size) {
        List<Position> moves = new ArrayList<>();
        for (int row = rowPosition - 1, col = columnPosition - 1; row >= 0 && col >= 0; row--, col--) {
            moves.add(new Position(row, col));
        }
        return moves;
    }

    private Iterable<Position> getUpperRightDiagonalMoves(int rowPosition, int columnPosition, int size) {
        List<Position> moves = new ArrayList<>();
        for (int row = rowPosition - 1, col = columnPosition + 1; row >= 0 && col < size; row--, col++) {
            moves.add(new Position(row, col));
        }
        return moves;
    }

    private Iterable<Position> getLowerLeftDiagonalMoves(int rowPosition, int columnPosition, int size) {
        List<Position> moves = new ArrayList<>();
        for (int row = rowPosition + 1, col = columnPosition - 1; row < size && col >= 0; row++, col--) {
            moves.add(new Position(row, col));
        }
        return moves;
    }

    private Iterable<Position> getLowerRightDiagonalMoves(int rowPosition, int columnPosition, int size) {
        List<Position> moves = new ArrayList<>();
        for (int row = rowPosition + 1, col = columnPosition + 1; row < size && col < size; row++, col++) {
            moves.add(new Position(row, col));
        }
        return moves;
    }

}
