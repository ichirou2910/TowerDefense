package towerDefense.entity.towers;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;
import towerDefense.GameField;

public class MachineGunTower extends TowerClass {
    private String type = "MachineGun";

    public MachineGunTower(Pane layer, GameField gf, Image image, double posX, double posY, double range) {
        super(layer, gf, image, posX, posY, range, Config.MACHINE_TOWER_DAMAGE, Config.MACHINE_TOWER_PRICE);
    }
    public String getType() {return this.type;}
}
