package towerDefense.entity.enemies;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;

public class BossEnemy extends EnemyClass {
    public BossEnemy(Pane layer, Image image, long tick) {
        super(layer, image, tick, Config.BOSS_HEALTH, Config.BOSS_ARMOR, Config.BOSS_SPEED, Config.BOSS_REWARD);

    }
}
