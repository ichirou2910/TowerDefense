/** Abstract class for enemies
 */

package towerDefense.entity.enemies;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.Config;
import towerDefense.EntityClass;
import towerDefense.ui.*;

public abstract class EnemyClass extends EntityClass {

    private int health;
    private int maxHealth;
    private int armor;
    private double speed;
    
    private int reward;

    private HealthBar healthBar = new HealthBar(this.getLayer(), this.getPosX(), this.getPosY() - 5);

    protected EnemyClass(Pane layer, Image image, int health, int armor, double speed, int reward){
        super(layer, image, Config.SPAWN_POS_X, Config.SPAWN_POS_Y, Config.SPAWN_ROTATION);
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
    
    public HealthBar getHealthBar() {return this.healthBar;}
    //#endregion

    public void update()
    {
        super.update();
        healthBar.update(this.health, this.maxHealth, this.getMidX(), this.getPosY());
        // destroy enemy
        if (this.getHealth() <= 0)
        {
            healthBar.destroy(this.getLayer());
            this.setDestroyed(true);
        }
    }
}
