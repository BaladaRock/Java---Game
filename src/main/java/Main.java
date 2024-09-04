import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ChessGameModel;
import view.ChessGameView;
import controller.ChessGameController;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        int  boardSize = 8;
        ChessGameModel chessGameModel = new ChessGameModel(boardSize);
        ChessGameView chessGameView = new ChessGameView(boardSize);
        ChessGameController gameController = new ChessGameController(chessGameModel, chessGameView);

        Scene scene = new Scene(chessGameView, 500, 500);
        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
