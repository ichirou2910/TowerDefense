package towerDefense;

import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import towerDefense.entity.towers.MachineGunTower;
import towerDefense.entity.towers.NormalTower;
import towerDefense.entity.towers.SniperTower;
import towerDefense.entity.towers.TowerClass;
import towerDefense.ui.GameLog;

import java.util.List;

/**
 * Player
 */
public class Player {

    private GameField gameField;                   // reference to GameField
    private GameLog log;

    private Text moneyUI = new Text(953, 285, "0");
    private Text livesUI = new Text(43, 0, "");

    private int money;
    private int health;
    private int level;


    // Getters & Setters
    //#region
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {this.money = money;}

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
    //#endregion

    public Player(Pane layer, GameField gf, GameLog log, int money, int health, int level)
    {
        this.gameField = gf;
        this.log = log;
        this.money = money;
        this.health = health;
        this.level = level;

        moneyUI.setTextOrigin(VPos.TOP);
        moneyUI.setStroke(Color.WHITE);
        moneyUI.setFill(Color.WHITE);
        moneyUI.setFont(Config.UI_FONT);

        livesUI.setTextOrigin(VPos.BOTTOM);
        livesUI.setStroke(Color.WHITE);
        livesUI.setFill(Color.WHITE);
        livesUI.setFont(Config.UI_FONT);
        // TODO: import style with CSS
//        moneyUI.setStyle("-fx-text-fill: white; -fx-font-size: 30px; -fx-font-family: \"VT323\"; -fx-background-color: transparent;");
        layer.getChildren().addAll(moneyUI, livesUI);
    }

    public void update()
    {
        moneyUI.setText("$" + money);
        livesUI.setText("Lives: " + health);
//        System.out.println("it's updated");
    }

    // Buy Tower function
    public void buyTower(Pane layer, String name, double posX, double posY, List<TowerClass> towers)
    {
        if (name.equals("Normal"))
        {
            if (money >= Config.NORMAL_TOWER_PRICE) {
                TowerClass t = new NormalTower(layer, gameField, log, new Image(Config.NORMAL_TOWER_IMAGE), posX, posY, Config.NORMAL_TOWER_RANGE, this);
                towers.add(t);
                money -= t.getPrice();
                log.addMessage("> Bought " + t.getType() + ". Spent $" + t.getPrice());
            } else {
                log.addMessage("> Not enough money to buy. Need $" + Config.NORMAL_TOWER_PRICE);
            }
        }
        else if (name.equals("Sniper"))
        {
            if (money >= Config.SNIPER_TOWER_PRICE) {
                TowerClass t = new SniperTower(layer, gameField, log, new Image(Config.SNIPER_TOWER_IMAGE), posX, posY, Config.SNIPER_TOWER_RANGE, this);
                towers.add(t);
                money -= t.getPrice();
                log.addMessage("> Bought " + t.getType() + ". Spent $" + t.getPrice());
            } else {
                log.addMessage("> Not enough money to buy. Need $" + Config.SNIPER_TOWER_PRICE);
            }
        }
        else if (name.equals("Machine"))
        {
            if (money >= Config.MACHINE_TOWER_PRICE) {
                TowerClass t = new MachineGunTower(layer, gameField, log, new Image(Config.MACHINE_TOWER_IMAGE), posX, posY, Config.MACHINE_TOWER_RANGE, this);
                towers.add(t);
                money -= t.getPrice();
                log.addMessage("> Bought " + t.getType() + ". Spent $" + t.getPrice());
            } else {
                log.addMessage("> Not enough money to buy. Need $" + Config.MACHINE_TOWER_PRICE);
            }
        }

    }

    public void takeReward(int reward, String type)
    {
        if (reward != 0) {
            money += reward;
            log.addMessage("> Defeated " + type + ". Received $" + reward);
        }
    }

    // TODO: fix base function
    public void fix()
    {

    }

}