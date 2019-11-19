package towerDefense.entity.enemies;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;

public class BossEnemy extends EnemyClass {
    public BossEnemy(Pane layer, Image image) {
        super(layer, image, "Boss Enemy", Config.BOSS_HEALTH, Config.BOSS_ARMOR, Config.BOSS_SPEED, Config.BOSS_REWARD);
    }
}
