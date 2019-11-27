package towerDefense;

import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import towerDefense.ui.GameLog;
import towerDefense.utilities.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Pane layer;
    private Scene scene;
    private List<EntityClass> entities = new ArrayList<>();     // manages game entities
    private GameStage gs = new GameStage(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, entities, 3, 1);
    private GameField gf = new GameField(gs);

    private Image screen;
    private Image title;
    private Image playNotPressed;
    private Image playPressed;

    // testing purposes
    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0;
    private boolean arrayFilled = false;

    @Override
    public void start(Stage primaryStage) {
        // configure the game window
        Group root = new Group();
        layer = new Pane();

        root.getChildren().add(layer);
        scene = new Scene(root, (double)Config.SCREEN_WIDTH, (double)Config.SCREEN_HEIGHT);

        //Game menu
        Group menu = new Group();
        Scene menuScene = new Scene(menu, (double) Config.SCREEN_WIDTH, (double) Config.SCREEN_HEIGHT);

        loadMenu();

        Sprite splashScreen = new Sprite(screen, 0, 0, false);
        Sprite logo = new Sprite(title, 100, 105, false);
        Sprite notPressed = new Sprite(playNotPressed, 425, 550, false);
        Sprite pressed = new Sprite(playPressed, 390, 513, false);
        pressed.getImageView().setVisible(false);
        menu.getChildren().addAll(splashScreen.getImageView(), logo.getImageView(), notPressed.getImageView(), pressed.getImageView());
        
        //Moving background
        Path path = new Path();
        MoveTo start = new MoveTo(400, 400);
        LineTo line1 = new LineTo(600, 500);
        LineTo line2 = new LineTo(350, 300);
        path.getElements().addAll(start, line1, line2);

        PathTransition pT = new PathTransition();
        pT.setNode(splashScreen.getImageView());
        pT.setPath(path);
        pT.setDuration(Duration.millis(22000));
        pT.setAutoReverse(true);
        pT.setCycleCount(100);
        pT.play();

        //Play game
        notPressed.getImageView().setOnMouseEntered(event -> {
            notPressed.getImageView().setVisible(false);
            pressed.getImageView().setVisible(true);
        });
        pressed.getImageView().setOnMouseExited(event -> {
            pressed.getImageView().setVisible(false);
            notPressed.getImageView().setVisible(true);
        });

        pressed.getImageView().setOnMouseClicked(event -> {

            // Show FPS
            Label fpsLabel = new Label();
            root.getChildren().add(fpsLabel);

            primaryStage.setScene(scene);

            loadGame();
            GameLog log = new GameLog(layer);
            Player p = new Player(layer, gf, log, 0, 100, 1);
            MenuController c = new MenuController(layer, gf, gs, p);

            c.init();

            // handle game loop
            new AnimationTimer(){

                @Override
                public void handle(long now) {
                    // FPS
                    long oldFrameTime = frameTimes[frameTimeIndex];
                    frameTimes[frameTimeIndex] = now;
                    frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length;
                    if (frameTimeIndex == 0)
                        arrayFilled = true;
                    if (arrayFilled)
                    {
                        long elapsedNanos = now - oldFrameTime;
                        long elapsedNanosPerFrame = elapsedNanos / frameTimes.length;
                        double frameRate = 1_000_000_000.0 / elapsedNanosPerFrame;
                        fpsLabel.setTextFill(Color.WHITE);
                        fpsLabel.setText(String.format("Current FPS: %.0f", frameRate));

                    }

                    gf.loadQueue(layer, p, log);
                    gf.spawnEnemies(layer);
                    gf.update(layer, p);
                    p.update();
                    log.update();
                    gf.buildBase(layer, p);
                    gf.gameOver(layer, p);
                    gf.waveOver(log);
                }
            }.start();
        });
        
        primaryStage.setTitle(Config.GAME_NAME);
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    private void loadMenu()
    {
        screen = new Image("file:res/images/Menu.png");
        title = new Image("file:res/images/Logo.png");
        playNotPressed = new Image("file:res/images/NotPressed.png");
        playPressed = new Image("file:res/images/Pressed.png");
    }

    // Load resources
    public void loadGame()
    {
        Image map = new Image("file:res/images/TowerDefense.png");
        ImageView mapBG = new ImageView(map);
        layer.getChildren().addAll(mapBG);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
