package towerDefense.entity.enemies;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;

public class TankerEnemy extends EnemyClass{
    public TankerEnemy(Pane layer, Image image, long tick) {
        super(layer, image, tick, Config.TANKER_HEALTH, Config.TANKER_ARMOR, Config.TANKER_SPEED, Config.TANKER_REWARD);

    }
}
