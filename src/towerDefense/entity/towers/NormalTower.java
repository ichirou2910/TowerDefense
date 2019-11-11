package towerDefense.entity.towers;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class NormalTower extends TowerClass {
    public NormalTower(Pane layer, Image image, long tick, double range, double speed) {
        super(layer, image, tick, range, speed);
    }
}
