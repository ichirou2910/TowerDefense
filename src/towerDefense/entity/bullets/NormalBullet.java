package towerDefense.entity.bullets;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class NormalBullet extends BulletClass {
    protected NormalBullet(Pane layer, Image image, long tick, double posX, double posY, double rotation, double width, double height, double damage)
    {
        super(layer, image, tick, posX, posY, rotation, width, height, damage);
    }
}
