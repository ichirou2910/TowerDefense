package towerDefense.entity.tiles.spawner;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;
import towerDefense.entity.enemies.TankerEnemy;

/**
 * TankerSpawner
 */
public class TankerSpawner extends SpawnerClass<TankerEnemy> {;

    public TankerSpawner(Pane layer, Image image, double spawnTime)
    {
        super(layer, image, spawnTime, Config.SPAWN_POS_X, Config.SPAWN_POS_Y, Config.SPAWN_ROTATION);
    }

    protected TankerEnemy spawn(Pane layer, Image image)
    {
        return new TankerEnemy(layer, image);
    }
}