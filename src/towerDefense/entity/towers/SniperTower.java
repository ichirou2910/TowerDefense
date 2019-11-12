package towerDefense.entity.towers;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;

public class SniperTower extends TowerClass {
    private String type = "Sniper";

    public SniperTower(Pane layer, Image image, double posX, double posY, double range) {
        super(layer, image, posX, posY, range, Config.SNIPER_TOWER_DAMAGE);
    }
    public String getType() {return this.type;}
}
