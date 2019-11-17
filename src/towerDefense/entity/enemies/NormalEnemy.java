package towerDefense.entity.enemies;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;

public class NormalEnemy extends EnemyClass {
    public NormalEnemy(Pane layer, Image image) {
        super(layer, image, Config.NORMAL_HEALTH, Config.NORMAL_ARMOR, Config.NORMAL_SPEED, Config.NORMAL_REWARD);
    }
}
