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
import towerDefense.entity.Sprite;

public class Main extends Application {
    
    Pane layer;
    Scene scene;
    List<Sprite> sprites = new ArrayList<Sprite>();

    @Override
    public void start(Stage primaryStage) {
        // final Canvas canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        // final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        // // final Controller gameController = new Controller(graphicsContext);

        // canvas.setFocusTraversable(true);
        // graphicsContext.setFontSmoothingType(FontSmoothingType.LCD);

        Group root = new Group();
        layer = new Pane();

        root.getChildren().add(layer);
        scene = new Scene(root, (double)Config.SCREEN_WIDTH, (double)Config.SCREEN_HEIGHT);
        

        primaryStage.setResizable(false);
        primaryStage.setTitle(Config.GAME_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();

        loadMap();

        AnimationTimer loop = new AnimationTimer(){
        
            @Override
            public void handle(long arg0) {
                sprites.forEach(sprite -> sprite.update());
            }
        };
        loop.start();
    }

    public void loadMap()
    {
        Image map = new Image("./res/images/TowerDefense.png");
        ImageView mapBG = new ImageView(map);
        layer.getChildren().addAll(mapBG);
    }

    public static void main(String[] args) {
        launch(args);
    }
}