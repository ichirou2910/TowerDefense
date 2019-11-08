package towerDefense.entity.enemies;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;

public class NormalEnemy extends EnemyClass {
    public NormalEnemy(Pane layer, Image image, long tick) {
        super(layer, image, tick, Config.NORMAL_HEALTH, Config.NORMAL_ARMOR, Config.NORMAL_SPEED, Config.NORMAL_REWARD);

    }
}
