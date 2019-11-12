package towerDefense.entity.towers;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;

public class SniperTower extends TowerClass {
    protected SniperTower(Pane layer, Image image, double range, double speed) {
        super(layer, image, range, speed, Config.SNIPER_TOWER_DAMAGE);
    }
}
