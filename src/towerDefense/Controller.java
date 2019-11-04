package towerDefense;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Controller {
    private final GraphicsContext graphicsContext;

    private GameField field;



    public Controller(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        final int width = Config.SCREEN_WIDTH;
        final int height = Config.SCREEN_HEIGHT;


    }
}
