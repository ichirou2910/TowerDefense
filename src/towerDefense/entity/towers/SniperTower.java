package towerDefense.entity.towers;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SniperTower extends TowerClass {
    protected SniperTower(Pane layer, Image image, long tick, double posX, double posY, double rotation, double width, double height
            , double range, double speed) {
        super(layer, image, tick, posX, posY, rotation, width, height, range, speed);
    }
}