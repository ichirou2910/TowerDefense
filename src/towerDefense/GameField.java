package towerDefense;

import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.entity.enemies.*;

public class GameField {
    private final double width;
    private final double height;
    private long tick;
    private final List<EntityClass> entities = new ArrayList<>(Config.TILES_TOTAL);
    //Timer for spawning, will be imported to class Spawner SoonTM
    private int timer = 0;

    public GameField(GameStage gameStage) {
        this.width = gameStage.getWidth();
        this.height = gameStage.getWidth();
        this.tick = 0;
        entities.addAll(gameStage.getEntities());
    }

    // Getters
    //#region
    public final double getWidth() {
        return width;
    }

    public final double getHeight() {
        return height;
    }

    public final long getTick() {
        return tick;
    }
    //#endregion

    public void spawnNormalEnemies(Pane layer, Image image)
    {
        if(timer == 0)
        {
            EnemyClass e = new NormalEnemy(layer, image, 10, 5 * 46 - 23, 46, 90, 46, 46, Config.NORMAL_HEALTH, Config.NORMAL_ARMOR, Config.NORMAL_SPEED, Config.NORMAL_REWARD);
            entities.add(e);
            timer = 100;
        }
        timer--;
    }

    public void spawnSmallerEnemies(Pane layer, Image image)
    {
        if(timer == 0) {
            EnemyClass e = new SmallerEnemy(layer, image, 10, 5 * 46 - 23, 46, 90, 46, 46, Config.SMALLER_HEALTH, Config.SMALLER_ARMOR, Config.SMALLER_SPEED, Config.SMALLER_REWARD);
            entities.add(e);
            timer = 100;
        }
        timer--;
    }

    public void spawnTankerEnemies(Pane layer, Image image)
    {
        if(timer == 0) {
            EnemyClass e = new SmallerEnemy(layer, image, 10, 5 * 46 - 23, 46, 90, 46, 46, Config.TANKER_HEALTH, Config.TANKER_ARMOR, Config.TANKER_SPEED, Config.TANKER_REWARD);
            entities.add(e);
            timer = 100;
        }
        timer--;
    }

    public void spawnBoss(Pane layer, Image image)
    {
        if(timer == 0) {
            EnemyClass e = new BossEnemy(layer, image, 10, 5 * 46 - 23, 46, 90, 46, 46, Config.BOSS_HEALTH, Config.BOSS_ARMOR, Config.BOSS_SPEED, Config.BOSS_ARMOR);
            entities.add(e);
            timer = 100;
        }
        timer--;
    }

    public void update()
    {
        entities.forEach(e -> e.move());
        entities.forEach(e -> e.update());
    }

    // only needed for enemies removal
    // TODO: 
    public void removeSprites()
    {
        Iterator<EntityClass> iter = entities.iterator();
        while( iter.hasNext()) {
            EntityClass en = iter.next();
            en.removeFromLayer();
            iter.remove();
        }
    }

}
