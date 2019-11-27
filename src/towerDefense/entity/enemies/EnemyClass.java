/** Abstract class for enemies
 */

package towerDefense.entity.enemies;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import towerDefense.Config;
import towerDefense.EntityClass;
import towerDefense.ui.HealthBar;

import java.time.Duration;

import static towerDefense.Config.TILE_SIZE;

public abstract class EnemyClass extends EntityClass {

    private String type;

    private double health;
    private double maxHealth;
    private double armor;
    private double speed;
    
    private int reward;

    private int moveSet = 1;

    private HealthBar healthBar = new HealthBar(this.getLayer(), this.getPosX(), this.getPosY() - 5);

    protected EnemyClass(Pane layer, Image image, String type, double health, double armor, double speed, int reward){
        super(layer, image, Config.SPAWN_POS_X, Config.SPAWN_POS_Y, Config.SPAWN_ROTATION);
        this.type = type;
        this.health = health;
        this.maxHealth = health;
        this.armor = armor;
        this.speed = speed;
        this.reward = reward;
    }
    
    // Getters & Setters
    //#region
    public void setHealth(double health) {this.health = health;}
    public void setArmor(double armor) {this.armor = armor;}
    public void setSpeed(double speed) {this.speed = speed;}
    public void setReward (int reward) {this.reward = reward;}
    
    public double getHealth() {return this.health;}
    public double getArmor() {return this.armor;}
    public double getSpeed() {return this.speed;}
    public int getReward() {return this.reward;}
    public String getType() {return this.type;}
    
    public HealthBar getHealthBar() {return this.healthBar;}
    //#endregion

    public void update()
    {
        super.update();
        healthBar.update(this.health, this.maxHealth, this.getMidX(), this.getMidY() - getImage().getHeight() / 2);
        if (this.getHealth() <= 0)
        {
            healthBar.destroy(this.getLayer());
            this.setDestroyed(true);
        }
    }

    public void move()
    {
        //Change rotation
        if(getMidX() == 5*TILE_SIZE && getMidY() == 5*TILE_SIZE) {
            setRotation(0);
            moveSet = 2;
        }
        if(getMidX() == 16*TILE_SIZE && getMidY() == 5*TILE_SIZE) {
            setRotation(90);
            moveSet = 1;
        }
        if(getMidX() == 16*TILE_SIZE && getMidY() == 11*TILE_SIZE) {
            setRotation(180);
            moveSet = 3;
        }
        if(getMidX() == 2*TILE_SIZE && getMidY() == 11*TILE_SIZE) {
            setRotation(90);
            moveSet = 1;
        }
        if(getMidX() == 2*TILE_SIZE && getMidY() == 15*TILE_SIZE) {
            setRotation(0);
            moveSet = 2;
        }
        if(moveSet == 1) {
            setMidY(getMidY() + getSpeed());
        }
        if(moveSet == 2) {
            setMidX(getMidX() + getSpeed());
        }
        if(moveSet == 3) {
            setMidX(getMidX() - getSpeed());
        }
    }

    // TODO: destroy on reaching base + decrease player's health
}
