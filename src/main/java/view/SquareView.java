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
    private final ChessGameView parentView;
    private String piece;
    private final ImageView imageView;

    private double initialMouseX;
    private double initialMouseY;
    private double initialPieceX;
    private double initialPieceY;
    private boolean draggingPiece = false;
    private final int tileSize;

    public SquareView(int row, int col, int tileSize, ChessGameView parentView) {
        this.row = row;
        this.col = col;
        this.piece = "";
        this.tileSize = tileSize;
        this.imageView = new ImageView();
        this.parentView = parentView;

        backgroundSquare = new Rectangle(tileSize, tileSize);
        initialColor = (row + col) % 2 == 0 ? Color.CHOCOLATE : Color.BROWN;
        backgroundSquare.setFill(initialColor);

        getChildren().addAll(backgroundSquare, imageView);

        setOnMousePressed(event -> startDrag(event.getSceneX(), event.getSceneY()));
        setOnMouseDragged(event -> onDrag(event.getSceneX(), event.getSceneY()));
        setOnMouseReleased(event -> finishDrag());
    }

    private void startDrag(double mouseX, double mouseY) {
        initialMouseX = mouseX;
        initialMouseY = mouseY;
        initialPieceX = imageView.getTranslateX();
        initialPieceY = imageView.getTranslateY();

        this.toFront();
        handleClick();
    }


    private void onDrag(double mouseX, double mouseY) {
        draggingPiece = true;
        double translatedXPosition = initialPieceX + mouseX - initialMouseX;
        double translatedYPosition = initialPieceY + mouseY - initialMouseY;
        imageView.setTranslateX(calculateTranslatedPosition(tileSize, translatedXPosition, col));
        imageView.setTranslateY(calculateTranslatedPosition(tileSize, translatedYPosition, row));
    }

    private double calculateTranslatedPosition(double tileSize, double translatedPosition, int coordinate) {
        double minCoordinate = -coordinate * tileSize;
        double maxCoordinate = (parentView.getBoardSize() - 1 - coordinate) * tileSize;

        return Math.max(minCoordinate, Math.min(translatedPosition, maxCoordinate));
    }

    private void finishDrag() {
        // Calculate the mouse coordinates relative to the chess board
        double translatedX = col * tileSize + imageView.getTranslateX();
        double translatedY = row * tileSize + imageView.getTranslateY();

        if (draggingPiece) {
            parentView.handlePieceDrop(this, translatedX, translatedY);
        }
        draggingPiece = false;

        // Get piece to its initial position
        imageView.setTranslateX(0);
        imageView.setTranslateY(0);

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

    private void handleClick() {
        parentView.handleSquareClick(this);
    }

    private void updatePieceImage() {
        if (piece != null && !piece.isEmpty()) {
            String imagePath = "/chess_images/" + piece + ".png";
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            imageView.setImage(image);
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
