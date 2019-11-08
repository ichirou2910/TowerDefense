package towerDefense.entity.tiles.spawner;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;
import towerDefense.entity.enemies.BossEnemy;

/**
 * BossSpawner
 */
public class BossSpawner extends SpawnerClass<BossEnemy> {;

    public BossSpawner(Pane layer, Image image, long tick, double spawnTime)
    {
        super(layer, image, tick, spawnTime, Config.SPAWN_POS_X, Config.SPAWN_POS_Y, Config.SPAWN_ROTATION);
    }

    protected BossEnemy spawn(Pane layer, Image image, long tick)
    {
        return new BossEnemy(layer, image, tick);
    }
}