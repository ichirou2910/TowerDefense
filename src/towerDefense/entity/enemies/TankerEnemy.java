package towerDefense.entity.enemies;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;

public class TankerEnemy extends EnemyClass{
    public TankerEnemy(Pane layer, Image image) {
        super(layer, image, "Tanker Enemy", Config.TANKER_HEALTH, Config.TANKER_ARMOR, Config.TANKER_SPEED, Config.TANKER_REWARD);

    }
}
