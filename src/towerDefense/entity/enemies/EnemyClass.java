/** Abstract class for enemies
 */

package towerDefense.entity.enemies;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;
import towerDefense.EntityClass;
import towerDefense.ui.HealthBar;

import static towerDefense.Config.TILE_SIZE;

public abstract class EnemyClass extends EntityClass {

    private String type;

    private int health;
    private int maxHealth;
    private int armor;
    private double speed;
    
    private int reward;

    private int moveSet = 1;

    private HealthBar healthBar = new HealthBar(this.getLayer(), this.getPosX(), this.getPosY() - 5);

    protected EnemyClass(Pane layer, Image image, String type, int health, int armor, double speed, int reward){
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
    public void setHealth(int health) {this.health = health;}
    public void setArmor(int armor) {this.armor = armor;}
    public void setSpeed(double speed) {this.speed = speed;}
    public void setReward (int reward) {this.reward = reward;}
    
    public int getHealth() {return this.health;}
    public int getArmor() {return this.armor;}
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
        if(getMidX() == 18*TILE_SIZE && getMidY() == 15*TILE_SIZE) {
            setRotation(90);
            moveSet = 1;
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
//        setPosX(getMidX() - getImage().getWidth() / 2);
//        setPosY(getPosY() - getImage().getHeight() / 2);
//        setMidX(getPosX() + getImage().getWidth() / 2);
//        setMidY(getPosY() + getImage().getHeight() / 2);
    }

    // TODO: destroy on reaching base + decrease player's health
}
