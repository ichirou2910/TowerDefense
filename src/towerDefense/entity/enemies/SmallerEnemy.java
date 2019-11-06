package towerDefense.entity.enemies;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SmallerEnemy extends EnemyClass{
    public SmallerEnemy(Pane layer, Image image, long tick, double posX, double posY, double rotation, double width, double height
            , int health, int armor, double speed, int reward) {
        super(layer, image, tick, posX, posY, rotation, width, height, health, armor, speed, reward);
    }
}
