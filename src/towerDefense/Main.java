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
import towerDefense.entity.enemies.*;

public class Main extends Application {
    
    Pane layer;
    Scene scene;
    List<EntityClass> entities = new ArrayList<>();
    GameStage gs = new GameStage(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, entities);
    GameField gf = new GameField(gs);

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();
        layer = new Pane();

        root.getChildren().add(layer);
        scene = new Scene(root, (double)Config.SCREEN_WIDTH, (double)Config.SCREEN_HEIGHT);
        
        primaryStage.setTitle(Config.GAME_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();

        loadMap();
        gf.spawnEnemies(layer);

        AnimationTimer loop = new AnimationTimer(){
        
            @Override
            public void handle(long now) {
                entities.forEach(e -> e.move());
                entities.forEach(e -> e.update());
                removeSprites(entities);
                System.out.println(entities.get(0).getPosX());
            }
        };
        loop.start();
    }

    public void loadMap()
    {
        Image map = new Image("/res/images/TowerDefense.png");
        ImageView mapBG = new ImageView(map);
        layer.getChildren().addAll(mapBG);
    }

    public void removeSprites(List<EntityClass> e)
    {
        Iterator<EntityClass> iter = e.iterator();
        while( iter.hasNext()) {
            EntityClass en = iter.next();
            en.removeFromLayer();
            iter.remove();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}