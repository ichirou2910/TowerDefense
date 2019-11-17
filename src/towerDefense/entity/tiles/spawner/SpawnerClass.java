package towerDefense.entity.tiles.spawner;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.EntityClass;
import towerDefense.entity.enemies.EnemyClass;

/**
 * Spawner
 */
public abstract class SpawnerClass<T extends EnemyClass> extends EntityClass {

    public double spawnTime;

    protected SpawnerClass(Pane layer, Image image, double spawnTime, double posX, double posY, double rotation)
    {
        super(layer, image, posX, posY, rotation);
        this.spawnTime = spawnTime;
    }

    protected abstract T spawn(Pane layer, Image image);
}