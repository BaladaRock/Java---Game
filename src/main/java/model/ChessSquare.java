package model;

import javafx.scene.paint.Color;

public class ChessSquare {
    private final int row;
    private final int col;
    private final model.Color color;  // For visualization, optional
    private ChessPiece piece;

    public ChessSquare(int row, int col, model.Color color) {
        this.row = row;
        this.col = col;
        this.color = color;
        this.piece = null;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public model.Color getColor() {
        return color;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }
}