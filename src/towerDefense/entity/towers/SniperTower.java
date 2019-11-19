package towerDefense.entity.towers;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;
import towerDefense.GameField;
import towerDefense.Player;
import towerDefense.ui.GameLog;

public class SniperTower extends TowerClass {
    private String type = "Sniper";

    public SniperTower(Pane layer, GameField gf, GameLog log, Image image, double posX, double posY, double range, Player player) {
        super(layer, gf, log, image, "Sniper Tower", posX, posY, range, Config.SNIPER_TOWER_DAMAGE, Config.SNIPER_TOWER_PRICE, player);
    }
    public String getType() {return this.type;}
}
