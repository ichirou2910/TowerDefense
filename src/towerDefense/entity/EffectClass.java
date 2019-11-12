package towerDefense.entity;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.EntityClass;

public class EffectClass extends EntityClass {
    public EffectClass(Pane layer, Image image, double posX, double posY, double rotation)
    {
        super(layer, image, posX, posY, rotation);
    }

    @Override
    public double getSpeed() {
        return 0;
    }
}
