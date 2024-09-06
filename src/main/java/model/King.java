package model;
import java.util.List;

public class King extends ChessPiece {

    public King(String name, Color color) {
        super(name, color);
    }

    @Override
    public Iterable<Iterable<Position>> getAvailableMoves(int rowIndex, int colIndex) {
        this.rowStartingPosition = rowIndex;
        this.columnStartingPosition = colIndex;

        return List.of(
                generateMove(rowStartingPosition + 1, columnStartingPosition + 1),
                generateMove(rowStartingPosition + 1, columnStartingPosition),
                generateMove(rowStartingPosition + 1, columnStartingPosition - 1),
                generateMove(rowStartingPosition , columnStartingPosition + 1),
                generateMove(rowStartingPosition , columnStartingPosition - 1),
                generateMove(rowStartingPosition - 1, columnStartingPosition + 1),
                generateMove(rowStartingPosition - 1, columnStartingPosition ),
                generateMove(rowStartingPosition - 1, columnStartingPosition - 1)
        );

    }

    private Iterable<Position> generateMove(int row, int col) {
        System.out.println("Raw move for king: (" + row + ", " + col + " )");
        return List.of(new Position(row, col));
    }
}
