package towerDefense.entity.bullets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import towerDefense.EntityClass;

public abstract class BulletClass extends EntityClass {
    private double damage;

    protected BulletClass(Pane layer, Image image, long tick, double posX, double posY, double rotation, double width, double height, double damage)
    {
        super(layer, image, tick, posX, posY, rotation, width, height);
        this.damage = damage;
    }

    public double getDamage() { return this.damage; }
}
