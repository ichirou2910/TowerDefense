package towerDefense.entity.tiles.spawner;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;
import towerDefense.entity.enemies.SmallerEnemy;

/**
 * SmallerSpawner
 */
public class SmallerSpawner extends SpawnerClass<SmallerEnemy> {;

    public SmallerSpawner(Pane layer, Image image, long tick, double spawnTime)
    {
        super(layer, image, tick, spawnTime, Config.SPAWN_POS_X, Config.SPAWN_POS_Y, Config.SPAWN_ROTATION);
    }

    protected SmallerEnemy spawn(Pane layer, Image image, long tick)
    {
        return new SmallerEnemy(layer, image, tick);
    }
}