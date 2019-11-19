package towerDefense;

import java.util.*;

import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    Pane layer;
    Scene scene, menuScene;
    List<EntityClass> entities = new ArrayList<>();     // manages game entities
    GameStage gs = new GameStage(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, entities, 1, 1);
    GameField gf = new GameField(gs);
    
    Image map, screen, title, playNotPressed, playPressed;

    @Override
    public void start(Stage primaryStage) {

        
        // configure the game window
        Group root = new Group();
        layer = new Pane();

        root.getChildren().add(layer);
        scene = new Scene(root, (double)Config.SCREEN_WIDTH, (double)Config.SCREEN_HEIGHT);

        //Game menu
        Group menu = new Group();
        menuScene = new Scene(menu, (double)Config.SCREEN_WIDTH, (double)Config.SCREEN_HEIGHT);

        screen = new Image("file:res/images/Menu.png");
        title = new Image("file:res/images/Logo.png");
        playNotPressed = new Image("file:res/images/NotPressed.png");
        playPressed = new Image("file:res/images/Pressed.png");

        ImageView splashScreen = new ImageView(screen);
        splashScreen.relocate(0, 0);
        ImageView logo = new ImageView(title);
        logo.relocate(100, 105);
        ImageView notPressed = new ImageView(playNotPressed);
        notPressed.relocate(425, 550);
        ImageView pressed = new ImageView(playPressed);
        pressed.relocate(390, 513);
        pressed.setVisible(false);
        menu.getChildren().addAll(splashScreen, logo, notPressed, pressed);

        //Moving background
        Path path = new Path();
        MoveTo start = new MoveTo(400, 400);
        LineTo line1 = new LineTo(600, 500);
        LineTo line2 = new LineTo(200, 300);
        path.getElements().addAll(start, line1, line1);

        PathTransition pT = new PathTransition();
        pT.setNode(splashScreen);
        pT.setPath(path);
        pT.setDuration(Duration.millis(22000));
        pT.setAutoReverse(true);
        pT.setCycleCount(100);
        pT.play();

        //Play game
        notPressed.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                notPressed.setVisible(false);
                pressed.setVisible(true);
            }
        });
        pressed.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pressed.setVisible(false);
                notPressed.setVisible(true);
            }
        });

        pressed.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(scene);

                load();
                gf.loadQueue(layer, 1);
                Player p = new Player(layer, gf, 100, 100, 1);
                Controller c = new Controller(layer, gf, gs, p);
                c.init();

                // handle game loop
                AnimationTimer loop = new AnimationTimer(){

                    @Override
                    public void handle(long now) {
                        gf.spawnEnemies(layer);
                        gf.update(p);
                        p.update();
                    }
                };
                loop.start();
            }

        });

        primaryStage.setTitle(Config.GAME_NAME);
        primaryStage.setScene(menuScene);
        primaryStage.show();

        // Testing purpose
        // TODO: add player related things: money, lives
    }

    // Load resources
    public void load()
    {
        map = new Image("file:res/images/TowerDefense.png");
        ImageView mapBG = new ImageView(map);
        layer.getChildren().addAll(mapBG);
    }

    public static void main(String[] args) {
        launch(args);
    }
}