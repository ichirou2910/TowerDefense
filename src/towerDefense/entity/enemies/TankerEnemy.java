package towerDefense.entity.enemies;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class TankerEnemy extends EnemyClass{
    public TankerEnemy(Pane layer, Image image, long tick, double posX, double posY, double dx, double dy, double rotation, double width, double height
            , int health, int armor, double speed, int reward) {
        super(layer, image, tick, posX, posY, dx, dy, rotation, width, height, health, armor, speed, reward);
    }
}
