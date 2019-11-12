package towerDefense.entity.tiles.spawner;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;
import towerDefense.entity.enemies.NormalEnemy;

/**
 * NormalSpawner
 */
public class NormalSpawner extends SpawnerClass<NormalEnemy> {;

    public NormalSpawner(Pane layer, Image image, double spawnTime)
    {
        super(layer, image, spawnTime, Config.SPAWN_POS_X, Config.SPAWN_POS_Y, Config.SPAWN_ROTATION);
    }

    protected NormalEnemy spawn(Pane layer, Image image)
    {
        return new NormalEnemy(layer, image);
    }
}