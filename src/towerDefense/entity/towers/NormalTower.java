package towerDefense.entity.towers;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;
import towerDefense.GameField;
import towerDefense.Player;
import towerDefense.ui.GameLog;

public class NormalTower extends TowerClass {
    private final String type = "Normal";
    public NormalTower(Pane layer, GameField gf, GameLog log, Image image, double posX, double posY, double range, Player player) {
        super(layer, gf, log, image, "Normal Tower", posX, posY, range, Config.NORMAL_TOWER_DAMAGE, Config.NORMAL_TOWER_PRICE, player);
    }

    public String getType() {return this.type;}
}
