package model;

public abstract class ChessPiece {
    private final String _name;  // e.g., "bishop", "knight", etc.
    private final Color _color; // e.g., "white", "black"
    protected int rowStartingPosition;
    protected int columnStartingPosition;

    public ChessPiece(String name, Color color) {
        this._name = name;
        this._color = color;
    }

    public String getName() {
        return _name;
    }

    public Color getColor() {
        return _color;
    }

    // Abstract methods for movement rules, to be implemented by subclasses
    public abstract Iterable<Iterable<Position>> getAvailableMoves(int rowIndex, int colIndex, int size);

}
