package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquareView extends Rectangle {
    private final int row;
    private final int col;
    private String piece;
    private final ChessGameView mainChessGameView;

    public SquareView(int row, int col, int tileSize, ChessGameView parentView) {
        super(tileSize, tileSize);
        this.row = row;
        this.col = col;
        this.piece = "";
        this.mainChessGameView = parentView;

        setFill((row + col) % 2 == 0 ? Color.BEIGE : Color.BROWN);

        setOnMouseClicked(event -> handleClick());
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    private void handleClick() {
        mainChessGameView.handleSquareClick(this);
    }
}
