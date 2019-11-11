package towerDefense.entity.bullets;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;

public class NormalBullet extends BulletClass {
    public NormalBullet(Pane layer, Image image, long tick, double posX, double posY, double rotation)
    {
        super(layer, image, tick, posX, posY, rotation, Config.NORMAL_TOWER_DAMAGE);
    }
}
