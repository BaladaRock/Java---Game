package model;
import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {

    public Rook(String name, Color color) {
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
                getRightMoves(rowIndex, colIndex, size)
        );

    }

    private Iterable<Position> getUpMoves(int rowPosition, int columnPosition, int size) {
        List<Position> moves = new ArrayList<>();
        for (int row = rowPosition - 1; row >= 0; row--) {
            moves.add(new Position(row, columnPosition));
        }
        return moves;
    }

    private Iterable<Position> getDownMoves(int rowPosition, int columnPosition, int size) {
        List<Position> moves = new ArrayList<>();
        for (int row = rowPosition + 1; row < size; row++) {
            moves.add(new Position(row, columnPosition));
        }
        return moves;
    }

    private Iterable<Position> getLeftMoves(int rowPosition, int columnPosition, int size) {
        List<Position> moves = new ArrayList<>();
        for (int col = columnPosition - 1; col >= 0; col--) {
            moves.add(new Position(rowPosition, col));
        }
        return moves;
    }

    private Iterable<Position> getRightMoves(int rowPosition, int columnPosition, int size) {
        List<Position> moves = new ArrayList<>();
        for (int col = columnPosition + 1; col < size; col++) {
            moves.add(new Position(rowPosition, col));
        }
        return moves;
    }
}
