package towerDefense.entity.bullets;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;

public class SniperBullet extends BulletClass {
    private final double speed = 80;
    private final int rateOfFire = 70;

    public SniperBullet(Pane layer, Image image, double posX, double posY, double rotation)
    {
        super(layer, image, posX, posY, rotation, Config.SNIPER_TOWER_DAMAGE);
    }

    public double getSpeed() {return this.speed;}
    public int getRateOfFire() {return this.rateOfFire;}
}
