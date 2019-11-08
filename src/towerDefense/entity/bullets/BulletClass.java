package towerDefense.entity.bullets;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.EntityClass;

public abstract class BulletClass extends EntityClass {
    private double damage;

    protected BulletClass(Pane layer, Image image, long tick, double posX, double posY, double rotation, double damage)
    {
        super(layer, image, tick, posX, posY, rotation);
        this.damage = damage;
    }

    public double getDamage() { return this.damage; }
}
