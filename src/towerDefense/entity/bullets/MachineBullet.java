package towerDefense.entity.bullets;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;

public class MachineBullet extends BulletClass {
    private final double speed = 100;
    private final int rateOfFire = 1;

    public MachineBullet(Pane layer, Image image, double posX, double posY, double rotation)
    {
        super(layer, image, posX, posY, rotation, Config.MACHINE_TOWER_DAMAGE);
    }

    public double getSpeed() {return this.speed;}
    public int getRateOfFire() {return this.rateOfFire;}
}
