package towerDefense.entity.bullets;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.EntityClass;

public abstract class BulletClass extends EntityClass {
    private int damage;

    protected BulletClass(Pane layer, Image image, double posX, double posY, double rotation, int damage)
    {
        super(layer, image, posX, posY, rotation);
        this.damage = damage;
    }

    public int getDamage() { return this.damage; }
}
