package towerDefense.entity.towers;

import towerDefense.EntityClass;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public abstract class TowerClass extends EntityClass {

    private final double range;
    private final double speed;

    protected TowerClass(Pane layer, Image image, long tick, double posX, double posY, double rotation, double width, double height
                        , double range, double speed) {
        super(layer, image, tick, posX, posY, rotation, width, height);
        this.range = range;
        this.speed = speed;
    }

    //Getter
    public double getRange(double range) {return this.range;}
    public double getSpeed(double speed) {return this.speed;}

    public void move() {}
}
