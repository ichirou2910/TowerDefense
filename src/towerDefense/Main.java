package towerDefense;

import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import towerDefense.ui.GameLog;
import towerDefense.utilities.Sprite;

import java.util.ArrayList;
import java.util.List;

import static towerDefense.Config.TILE_SIZE;

public class Main extends Application {

    private Pane layer;
    private Scene scene;
    private List<EntityClass> entities = new ArrayList<>();     // manages game entities
    private GameStage gs = new GameStage(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, entities, 1, 1);
    private GameField gf = new GameField(gs);

    private Image screen;
    private Image title;
    private Image playNotPressed;
    private Image playPressed;

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

        Sprite splashScreen = new Sprite(screen, 0, 0);
        Sprite logo = new Sprite(title, 100, 105);
        Sprite notPressed = new Sprite(playNotPressed, 425, 550);
        Sprite pressed = new Sprite(playPressed, 390, 513);
        pressed.getImageView().setVisible(false);
        menu.getChildren().addAll(splashScreen.getImageView(), logo.getImageView(), notPressed.getImageView(), pressed.getImageView());
        
        //Moving background
        Path path = new Path();
        MoveTo start = new MoveTo(400, 400);
        LineTo line1 = new LineTo(600, 500);
        LineTo line2 = new LineTo(200, 300);
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
            primaryStage.setScene(scene);

            loadGame();
            gf.loadQueue(layer, 1);
            GameLog log = new GameLog(layer);
            Player p = new Player(layer, gf, log, 100, 100, 1);
            MenuController c = new MenuController(layer, gf, gs, p);

            c.init();

            // handle game loop
            new AnimationTimer(){

                @Override
                public void handle(long now) {
                    gf.spawnEnemies(layer);
                    gf.update(p);
                    p.update();
                    log.update();
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
