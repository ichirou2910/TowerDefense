package towerDefense.entity.towers;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;

public class MachineGunTower extends TowerClass {
    private String type = "MachineGun";

    public MachineGunTower(Pane layer, Image image, double posX, double posY, double range) {
        super(layer, image, posX, posY, range, Config.MACHINE_TOWER_DAMAGE);
    }
    public String getType() {return this.type;}
}
