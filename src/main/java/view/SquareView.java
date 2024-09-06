package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class SquareView extends StackPane {
    private final int row;
    private final int col;
    private String piece;
    private final ImageView imageView;

    public SquareView(int row, int col, int tileSize, ChessGameView parentView) {
        this.row = row;
        this.col = col;
        this.piece = "";
        this.imageView = new ImageView();

        var background = new Rectangle(tileSize, tileSize);
        background.setFill((row + col) % 2 == 0 ? Color.CHOCOLATE : Color.BROWN);

        getChildren().addAll(background, imageView);

        setOnMouseClicked(event -> handleClick(parentView));
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
        updatePieceImage();
    }

    private void handleClick(ChessGameView parentView) {
        parentView.handleSquareClick(this);
    }

    private void updatePieceImage() {
        if (piece != null && !piece.isEmpty()) {
            String imagePath = "/chess_images/" + piece + ".png";
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            imageView.setImage(image);
            imageView.setFitWidth(getWidth());
            imageView.setFitHeight(getHeight());
        } else {
            imageView.setImage(null);
        }
    }
}
