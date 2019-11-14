package towerDefense.entity.towers;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;
import towerDefense.GameField;

public class NormalTower extends TowerClass {
    private final String type = "Normal";
    public NormalTower(Pane layer, GameField gf, Image image, double posX, double posY, double range) {
        super(layer, gf, image, posX, posY, range, Config.NORMAL_TOWER_DAMAGE);
    }

    public String getType() {return this.type;}
}
