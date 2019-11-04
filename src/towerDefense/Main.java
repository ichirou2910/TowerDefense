package towerDefense;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.FontSmoothingType;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final Canvas canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        final Controller gameController = new Controller(graphicsContext);

        canvas.setFocusTraversable(true);
        graphicsContext.setFontSmoothingType(FontSmoothingType.LCD);

        primaryStage.setResizable(false);
        primaryStage.setTitle(Config.GAME_NAME);
        primaryStage.setScene(new Scene(new StackPane(canvas)));
        primaryStage.show();

    }
}