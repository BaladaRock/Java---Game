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

    private double initialMouseX;
    private double initialMouseY;
    private double initialPieceX;
    private double initialPieceY;
    private boolean draggingPiece = false;

    public SquareView(int row, int col, int tileSize, ChessGameView parentView) {
        this.row = row;
        this.col = col;
        this.piece = "";
        this.imageView = new ImageView();

        backgroundSquare = new Rectangle(tileSize, tileSize);
        initialColor = (row + col) % 2 == 0 ? Color.CHOCOLATE : Color.BROWN;
        backgroundSquare.setFill(initialColor);

        getChildren().addAll(backgroundSquare, imageView);

        setOnMousePressed(event -> startDrag(event.getSceneX(), event.getSceneY()));
        setOnMouseDragged(event -> onDrag(event.getSceneX(), event.getSceneY()));
        setOnMouseReleased(event -> finishDrag());

        setOnMouseClicked(event -> handleClick(parentView));
    }

    private void startDrag(double mouseX, double mouseY) {
        if (piece != null) {
            initialMouseX = mouseX;
            initialMouseY = mouseY;
            initialPieceX = imageView.getTranslateX();
            initialPieceY = imageView.getTranslateY();
            draggingPiece = true;

            this.toFront();
        }
    }


    private void onDrag(double mouseX, double mouseY) {
        if (draggingPiece && !piece.isEmpty()) {
            double deltaX = mouseX - initialMouseX;
            double deltaY = mouseY - initialMouseY;

            imageView.setTranslateX(initialPieceX + deltaX);
            imageView.setTranslateY(initialPieceY + deltaY);
        }
    }

    private void finishDrag() {
        if (draggingPiece && !piece.isEmpty()) {
            draggingPiece = false;
            // Get to initial position
            // To do: Add the move behaviour for drag and drop
            imageView.setTranslateX(0);
            imageView.setTranslateY(0);

        }
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
