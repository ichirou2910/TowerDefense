package towerDefense.entity.towers;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;

public class NormalTower extends TowerClass {
    private final String type = "Normal";
    public NormalTower(Pane layer, Image image, double range, double speed) {
        super(layer, image, range, speed, Config.NORMAL_TOWER_DAMAGE);
    }

    public String getType() {return this.type;}
}
