package towerDefense.entity.enemies;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;

public class SmallerEnemy extends EnemyClass{
    public SmallerEnemy(Pane layer, Image image, long tick) {
        super(layer, image, tick, Config.SMALLER_HEALTH, Config.SMALLER_ARMOR, Config.SMALLER_SPEED, Config.SMALLER_REWARD);

    }
}
