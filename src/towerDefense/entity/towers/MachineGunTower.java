package towerDefense.entity.towers;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;

public class MachineGunTower extends TowerClass {
    protected MachineGunTower(Pane layer, Image image, double range, double speed) {
        super(layer, image, range, speed, Config.MACHINE_TOWER_DAMAGE);
    }
}
