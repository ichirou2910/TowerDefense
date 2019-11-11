package towerDefense;

import java.util.*;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import towerDefense.entity.enemies.EnemyClass;
import towerDefense.entity.towers.NormalTower;
import towerDefense.entity.towers.TowerClass;

public class Main extends Application {


    Pane layer;
    Scene scene;
    List<EntityClass> entities = new ArrayList<>();     // manages game entities
    GameStage gs = new GameStage(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, entities, 1, 1);
    GameField gf = new GameField(gs);
    Image map, n_tower;

    @Override
    public void start(Stage primaryStage) {
        // configure the game window
        Group root = new Group();
        layer = new Pane();

        root.getChildren().add(layer);
        scene = new Scene(root, (double)Config.SCREEN_WIDTH, (double)Config.SCREEN_HEIGHT);

        primaryStage.setTitle(Config.GAME_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();

        List<EnemyClass> e = gf.getEnemies();

        load();
        gf.loadQueue(layer, 1);
        TowerClass t = new NormalTower(layer, new Image(Config.NORMAL_TOWER_IMAGE), 1, 136, 1);

        // handle game loop
        AnimationTimer loop = new AnimationTimer(){

            @Override
            public void handle(long now) {
                t.update();
                t.checkTarget();
                t.findTarget(e);
                t.move();
                gf.update(layer);
            }
        };
        loop.start();
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