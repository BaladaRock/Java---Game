package model;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {

    public Pawn(String name, Color color) {
        super(name, color);
    }

    @Override
    public Iterable<Iterable<Position>> getAvailableMoves(int rowIndex, int colIndex, int size) {
        this.rowStartingPosition = rowIndex;
        this.columnStartingPosition = colIndex;

        List<Iterable<Position>> moves = new ArrayList<>();

        if (getColor() == Color.white) {
            moves.add(getForwardMoveWhite(rowIndex, colIndex, size));
            moves.add(getDiagonalCaptureWhite(rowIndex, colIndex, size));
        } else {
            moves.add(getForwardMoveBlack(rowIndex, colIndex, size));
            moves.add(getDiagonalCaptureBlack(rowIndex, colIndex, size));
        }

        return moves;
    }

    private Iterable<Position> getForwardMoveWhite(int rowPosition, int columnPosition, int size) {
        List<Position> forwardMoves = new ArrayList<>();

        if (rowPosition > 0) {
            forwardMoves.add(new Position(rowPosition - 1, columnPosition));
        }

        if (rowPosition == size - 2) {
            forwardMoves.add(new Position(rowPosition - 2, columnPosition));
        }

        return forwardMoves;
    }

    private Iterable<Position> getForwardMoveBlack(int rowPosition, int columnPosition, int size) {
        List<Position> forwardMoves = new ArrayList<>();

        if (rowPosition < size - 1) {
            forwardMoves.add(new Position(rowPosition + 1, columnPosition));
        }

        if (rowPosition == 1) {
            forwardMoves.add(new Position(rowPosition + 2, columnPosition));
        }

        return forwardMoves;
    }

    private Iterable<Position> getDiagonalCaptureWhite(int rowPosition, int columnPosition, int size) {
        List<Position> captureMoves = new ArrayList<>();

        if (rowPosition > 0) {
            if (columnPosition > 0) {
                captureMoves.add(new Position(rowPosition - 1, columnPosition - 1));
            }
            if (columnPosition < size - 1) {
                captureMoves.add(new Position(rowPosition - 1, columnPosition + 1));
            }
        }

        return captureMoves;
    }

    private Iterable<Position> getDiagonalCaptureBlack(int rowPosition, int columnPosition, int size) {
        List<Position> captureMoves = new ArrayList<>();

        if (rowPosition < size - 1) {
            if (columnPosition > 0) {
                captureMoves.add(new Position(rowPosition + 1, columnPosition - 1));
            }
            if (columnPosition < size - 1) {
                captureMoves.add(new Position(rowPosition + 1, columnPosition + 1));
            }
        }

        return captureMoves;
    }
}
