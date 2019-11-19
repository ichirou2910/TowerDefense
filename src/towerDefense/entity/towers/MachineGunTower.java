package towerDefense.entity.towers;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;
import towerDefense.GameField;
import towerDefense.Player;
import towerDefense.ui.GameLog;

public class MachineGunTower extends TowerClass {
    private String type = "MachineGun";

    public MachineGunTower(Pane layer, GameField gf, GameLog log, Image image, double posX, double posY, double range, Player player) {
        super(layer, gf, log, image, "Machine Gun Tower", posX, posY, range, Config.MACHINE_TOWER_DAMAGE, Config.MACHINE_TOWER_PRICE, player);
    }
    public String getType() {return this.type;}
}
