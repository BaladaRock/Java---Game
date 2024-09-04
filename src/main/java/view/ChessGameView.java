package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderPane;

public class ChessGameView extends GridPane {
    private final int _size;
    private final int TILE_SIZE = 60;

    public ChessGameView(int boardSize) {
        _size = boardSize;
        setPadding(new Insets(10));
        setHgap(0);
        setVgap(0);

        for (int row = 0; row < _size; row++) {
            for (int col = 0; col < _size; col++) {
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
