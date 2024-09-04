package view;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChessGameView extends GridPane {
    private final int SIZE;
    private final int TILE_SIZE = 60;

    public ChessGameView(int boardSize) {
        SIZE = boardSize;
        setPadding(new Insets(10));
        setHgap(0);
        setVgap(0);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                if ((row + col) % 2 == 0) {
                    tile.setFill(Color.BEIGE);
                } else {
                    tile.setFill(Color.BROWN);
                }

                add(tile, col, row);
            }
        }
    }
}
