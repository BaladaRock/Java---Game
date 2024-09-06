package model;

public abstract class ChessPiece {
    private final String name;  // e.g., "bishop", "knight", etc.
    private final Color color; // e.g., "white", "black"
    protected int rowStartingPosition;
    protected int columnStartingPosition;

    public ChessPiece(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    // Abstract methods for movement rules, to be implemented by subclasses
    public abstract Iterable<Iterable<Position>> getAvailableMoves(int rowIndex, int colIndex, int size);
}
