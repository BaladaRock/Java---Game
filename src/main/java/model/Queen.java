package model;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {

    public Queen(String name, Color color) {
        super(name, color);
    }

    @Override
    public Iterable<Iterable<Position>> getAvailableMoves(int rowIndex, int colIndex, int size) {
        this.rowStartingPosition = rowIndex;
        this.columnStartingPosition = colIndex;

        return List.of(
                getUpMoves(rowIndex, colIndex, size),
                getDownMoves(rowIndex, colIndex, size),
                getLeftMoves(rowIndex, colIndex, size),
                getRightMoves(rowIndex, colIndex, size),
                getUpperLeftDiagonalMoves(rowIndex, colIndex, size),
                getUpperRightDiagonalMoves(rowIndex, colIndex, size),
                getLowerLeftDiagonalMoves(rowIndex, colIndex, size),
                getLowerRightDiagonalMoves(rowIndex, colIndex, size)
        );
    }

    // Up (Vertical)
    private Iterable<Position> getUpMoves(int rowPosition, int columnPosition, int size) {
        List<Position> moves = new ArrayList<>();
        for (int row = rowPosition - 1; row >= 0; row--) {
            moves.add(new Position(row, columnPosition));
        }
        return moves;
    }

    // Down (Vertical)
    private Iterable<Position> getDownMoves(int rowPosition, int columnPosition, int size) {
        List<Position> moves = new ArrayList<>();
        for (int row = rowPosition + 1; row < size; row++) {
            moves.add(new Position(row, columnPosition));
        }
        return moves;
    }

    // Left (Horizontal)
    private Iterable<Position> getLeftMoves(int rowPosition, int columnPosition, int size) {
        List<Position> moves = new ArrayList<>();
        for (int col = columnPosition - 1; col >= 0; col--) {
            moves.add(new Position(rowPosition, col));
        }
        return moves;
    }

    // Right (Horizontal)
    private Iterable<Position> getRightMoves(int rowPosition, int columnPosition, int size) {
        List<Position> moves = new ArrayList<>();
        for (int col = columnPosition + 1; col < size; col++) {
            moves.add(new Position(rowPosition, col));
        }
        return moves;
    }

    // Upper-left diagonal
    private Iterable<Position> getUpperLeftDiagonalMoves(int rowPosition, int columnPosition, int size) {
        List<Position> moves = new ArrayList<>();
        for (int row = rowPosition - 1, col = columnPosition - 1; row >= 0 && col >= 0; row--, col--) {
            moves.add(new Position(row, col));
        }
        return moves;
    }

    // Upper-right diagonal
    private Iterable<Position> getUpperRightDiagonalMoves(int rowPosition, int columnPosition, int size) {
        List<Position> moves = new ArrayList<>();
        for (int row = rowPosition - 1, col = columnPosition + 1; row >= 0 && col < size; row--, col++) {
            moves.add(new Position(row, col));
        }
        return moves;
    }

    // Lower-left diagonal
    private Iterable<Position> getLowerLeftDiagonalMoves(int rowPosition, int columnPosition, int size) {
        List<Position> moves = new ArrayList<>();
        for (int row = rowPosition + 1, col = columnPosition - 1; row < size && col >= 0; row++, col--) {
            moves.add(new Position(row, col));
        }
        return moves;
    }

    // Lower-right diagonal
    private Iterable<Position> getLowerRightDiagonalMoves(int rowPosition, int columnPosition, int size) {
        List<Position> moves = new ArrayList<>();
        for (int row = rowPosition + 1, col = columnPosition + 1; row < size && col < size; row++, col++) {
            moves.add(new Position(row, col));
        }
        return moves;
    }
}
