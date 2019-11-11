package towerDefense.entity.bullets;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class NormalBullet extends BulletClass {
    public NormalBullet(Pane layer, Image image, long tick, double posX, double posY, double rotation, double damage)
    {
        super(layer, image, tick, posX, posY, rotation, damage);
    }
}
