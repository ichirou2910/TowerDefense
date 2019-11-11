package towerDefense.entity.towers;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;

public class NormalTower extends TowerClass {
    public NormalTower(Pane layer, Image image, long tick, double range, double speed) {
        super(layer, image, tick, range, speed, Config.NORMAL_TOWER_DAMAGE);
    }
}
