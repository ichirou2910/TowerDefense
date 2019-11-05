package towerDefense.entity.enemies;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.EntityClass;

public abstract class EnemyClass extends EntityClass {

    private int health;
    private int armor;
    private double speed;
    private int reward;

    protected EnemyClass(Pane layer, Image image, long tick, double posX, double posY, double rotation, double width, double height
                        , int health, int armor, double speed, int reward){
        super(layer, image, tick, posX, posY, rotation, width, height);
        this.health = health;
        this.armor = armor;
        this.speed = speed;
        this.reward = reward;
    }
    //Setter
    public void setHealth(int health) {this.health = health;}
    public void setArmor(int armor) {this.armor = armor;}
    public void setSpeed(double speed) {this.speed = speed;}
    public void setReward (int reward) {this.reward = reward;}
    //Getter
    public int getHealth() {return this.health;}
    public int getArmor() {return this.armor;}
    public double getSpeed() {return this.speed;}
    public int getReward() {return this.reward;}
}
