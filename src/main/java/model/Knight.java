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
                getLMovementUpperLeftHorizontally(),
                getLMovementUpperLeftVertically(),
                getLMovementUpperRightHorizontally(),
                getLMovementUpperRightVertically(),
                getLMovementLowerLeftHorizontally(),
                getLMovementLowerLeftVertically(),
                getLMovementLowerRightHorizontally(),
                getLMovementLowerRightVertically()
        );

    }

    private Iterable<Position> getLMovementLowerRightVertically() {
        return List.of(
            new Position(rowStartingPosition + 2, columnStartingPosition + 1)
        );
    }

    private Iterable<Position> getLMovementLowerRightHorizontally() {
        return List.of(
                new Position(rowStartingPosition + 1, columnStartingPosition + 2)
        );
    }

    private Iterable<Position> getLMovementLowerLeftVertically() {
        return List.of(
                new Position(rowStartingPosition + 2, columnStartingPosition - 1)
        );
    }

    private Iterable<Position> getLMovementLowerLeftHorizontally() {
        return List.of(
                new Position(rowStartingPosition + 1, columnStartingPosition - 2)
        );
    }

    private Iterable<Position> getLMovementUpperRightVertically() {
        return List.of(
                new Position(rowStartingPosition - 2, columnStartingPosition + 1)
        );
    }

    private Iterable<Position> getLMovementUpperRightHorizontally() {
        return List.of(
                new Position(rowStartingPosition - 1, columnStartingPosition + 2)
        );
    }

    private Iterable<Position> getLMovementUpperLeftVertically() {
        return List.of(
                new Position(rowStartingPosition - 1, columnStartingPosition - 2)
        );
    }

    private Iterable<Position> getLMovementUpperLeftHorizontally() {
        return List.of(
                new Position(rowStartingPosition - 2, columnStartingPosition - 1)
        );
    }
}
