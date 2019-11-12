package towerDefense.entity.bullets;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;

public class SpawnBullet {
    public BulletClass createBullet(Pane layer, double rotation, String type) {
        BulletClass b = null;
        if(type == "Normal") {
            b = new NormalBullet(layer, new Image(Config.NORMAL_BULLET_IMAGE), 0, 0, rotation);
        }
        if(type == "Sniper") {
            b = new SniperBullet(layer, new Image(Config.SNIPER_BULLET_IMAGE), 0, 0, rotation);
        }
        if(type == "MachineGun") {
            b = new MachineBullet(layer, new Image(Config.MACHINE_BULLET_IMAGE), 0, 0, rotation);
            System.out.println(b.getRotation());
        }
        return b;
    }
}