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
    private final Rectangle backgroundSquare;
    private final Color initialColor;
    private String piece;
    private final ImageView imageView;

    public SquareView(int row, int col, int tileSize, ChessGameView parentView) {
        this.row = row;
        this.col = col;
        this.piece = "";
        this.imageView = new ImageView();

        backgroundSquare = new Rectangle(tileSize, tileSize);
        initialColor = (row + col) % 2 == 0 ? Color.CHOCOLATE : Color.BROWN;
        backgroundSquare.setFill(initialColor);

        getChildren().addAll(backgroundSquare, imageView);

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

    public void highlightSquare() {
        backgroundSquare.setFill(Color.DARKGRAY);
    }

    public void resetHighlightedSquare() {
        backgroundSquare.setFill(initialColor);
    }

}
