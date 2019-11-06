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

public class Main extends Application {
    
    Pane layer;
    Scene scene;
    List<EntityClass> entities = new ArrayList<>();
    GameStage gs = new GameStage(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, entities);
    GameField gf = new GameField(gs);
    Image map, n_Enemy;

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();
        layer = new Pane();

        root.getChildren().add(layer);
        scene = new Scene(root, (double)Config.SCREEN_WIDTH, (double)Config.SCREEN_HEIGHT);
        
        primaryStage.setTitle(Config.GAME_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();

        load();
        gf.spawnEnemies(layer, n_Enemy);

        AnimationTimer loop = new AnimationTimer(){
        
            @Override
            public void handle(long now) {
                gf.update();
                // gf.removeSprites();
            }
        };
        loop.start();
    }

    public void load()
    {
        map = new Image("/res/images/TowerDefense.png");
        ImageView mapBG = new ImageView(map);
        layer.getChildren().addAll(mapBG);

        n_Enemy = new Image("/res/images/enemies/Normal.png");
    }

    public static void main(String[] args) {
        launch(args);
    }
}