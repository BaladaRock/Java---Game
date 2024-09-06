package model;

public class ChessSquare {
    private final int _row;
    private final int _col;
    private final Color _color;  // For visualization, optional
    public Position _positionCoordinates;
    private ChessPiece _piece;

    public ChessSquare(int row, int col, model.Color color) {
        this._row = row;
        this._col = col;
        this._color = color;
        this._positionCoordinates = new Position(row, col);
        this._piece = null;
    }

    public int get_row() {
        return _row;
    }

    public int get_col() {
        return _col;
    }

    public model.Color get_color() {
        return _color;
    }

    public ChessPiece get_piece() {
        return _piece;
    }

    public void set_piece(ChessPiece _piece) {
        this._piece = _piece;
    }

    public Position get_positionCoordinates() {
        return _positionCoordinates;
    }
}