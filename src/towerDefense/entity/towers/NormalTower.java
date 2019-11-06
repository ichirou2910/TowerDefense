package towerDefense.entity.towers;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class NormalTower extends TowerClass {
    protected NormalTower(Pane layer, Image image, long tick, double posX, double posY, double dx, double dy, double rotation, double width, double height
            , double range, double speed) {
        super(layer, image, tick, posX, posY, dx, dy, rotation, width, height, range, speed);
    }
}
