package towerDefense;

import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import towerDefense.entity.towers.MachineGunTower;
import towerDefense.entity.towers.NormalTower;
import towerDefense.entity.towers.SniperTower;
import towerDefense.entity.towers.TowerClass;
import towerDefense.Config;
import java.util.List;

/**
 * Player
 */
public class Player {

    private GameField gameField;                   // reference to GameField
    private Text moneyUI = new Text(1, 0, "0");
    private Text livesUI = new Text(43, 0, "");
//    private TextUI playtimeUI = new TextUI("", 42, 0);

    private int money;
    private int health;
    private int level;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

//    public Player(Pane layer, GameField gf)
//    {
//        this.gameField = gf;
//        this.money = 100;
//        this.health = 100;
//        this.level = 1;
//
//        moneyUI.setStroke(Color.WHITE);
//        moneyUI.setStrokeWidth(2);
//        layer.getChildren().add(moneyUI);
////        layer.getChildren().add(playtimeUI.getText());
//    }

    public Player(Pane layer, GameField gf, int money, int health, int level)
    {
        this.gameField = gf;
        this.money = money;
        this.health = health;
        this.level = level;

        moneyUI.setTextAlignment(TextAlignment.JUSTIFY);
        moneyUI.setTextOrigin(VPos.TOP);
        moneyUI.setX(953);
        moneyUI.setY(285);
        moneyUI.setStroke(Color.WHITE);
        moneyUI.setFill(Color.WHITE);
        moneyUI.setFont(Config.UI_FONT);
        // TODO: import style with CSS
//        moneyUI.setStyle("-fx-text-fill: white; -fx-font-size: 30px; -fx-font-family: \"VT323\"; -fx-background-color: transparent;");
        layer.getChildren().add(moneyUI);
    }

    public void update()
    {
        moneyUI.setText("$" + money);
//        System.out.println("it's updated");
    }

    // TODO: buy a tower
    public void buyTower(Pane layer, String name, double posX, double posY, List<TowerClass> towers)
    {
        if (name.equals("Normal"))
        {
            if (money >= Config.NORMAL_TOWER_PRICE) {
                TowerClass t = new NormalTower(layer, gameField, new Image(Config.NORMAL_TOWER_IMAGE), posX, posY, Config.NORMAL_TOWER_RANGE);
                towers.add(t);
                money -= t.getPrice();
            }
        }
        else if (name.equals("Sniper"))
        {
            if (money >= Config.SNIPER_TOWER_PRICE) {
                TowerClass t = new SniperTower(layer, gameField, new Image(Config.SNIPER_TOWER_IMAGE), posX, posY, Config.SNIPER_TOWER_RANGE);
                towers.add(t);
                money -= t.getPrice();
            }
        }
        else if (name.equals("Machine"))
        {
            if (money >= Config.MACHINE_TOWER_PRICE) {
                TowerClass t = new MachineGunTower(layer, gameField, new Image(Config.MACHINE_TOWER_IMAGE), posX, posY, Config.MACHINE_TOWER_RANGE);
                towers.add(t);
                money -= t.getPrice();
            }
        }
    }

    // TODO: fix home
    public void fix()
    {

    }
}