package model;
import java.util.List;

public class Knight extends ChessPiece {

    public Knight(String name, Color color) {
        super(name, color);
    }

    @Override
    public Iterable<Iterable<Position>> getAvailableMoves(int rowIndex, int colIndex) {
        this.rowStartingPosition = rowIndex;
        this.columnStartingPosition = colIndex;

        return List.of(
                generateMove(rowStartingPosition + 2, columnStartingPosition + 1),
                generateMove(rowStartingPosition + 2, columnStartingPosition - 1),
                generateMove(rowStartingPosition - 2, columnStartingPosition + 1),
                generateMove(rowStartingPosition - 2, columnStartingPosition - 1),
                generateMove(rowStartingPosition + 1, columnStartingPosition + 2),
                generateMove(rowStartingPosition + 1, columnStartingPosition - 2),
                generateMove(rowStartingPosition - 1, columnStartingPosition + 2),
                generateMove(rowStartingPosition - 1, columnStartingPosition - 2)
        );

    }

    private Iterable<Position> generateMove(int row, int col) {
        System.out.println("Raw move for knight: (" + row + ", " + col + " )");
        return List.of(new Position(row, col));
    }
}
