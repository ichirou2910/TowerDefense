package towerDefense.entity.bullets;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import towerDefense.Config;

public class SpawnBullet {
    public BulletClass createBullet(Pane layer, double rotation, String type, int damage) {
        BulletClass b = null;
        if(type.equals("Normal")) {
            b = new NormalBullet(layer, new Image(Config.NORMAL_BULLET_IMAGE), -46, -46, rotation, damage);
        }
        if(type.equals("Sniper")) {
            b = new SniperBullet(layer, new Image(Config.SNIPER_BULLET_IMAGE), -46, -46, rotation);
        }
        if(type.equals("MachineGun")) {
            b = new MachineBullet(layer, new Image(Config.MACHINE_BULLET_IMAGE), -46, -46, rotation, damage);
        }
        return b;
    }
}