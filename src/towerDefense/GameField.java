package towerDefense;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import towerDefense.entity.enemies.*;
import towerDefense.entity.tiles.spawner.*;
import towerDefense.entity.towers.NormalTower;
import towerDefense.entity.towers.TowerClass;

public class GameField {
    private final double width;
    private final double height;
    private long tick;
    private final List<EntityClass> entities = new ArrayList<>();
    private Queue<Pair<String, Double>> enemiesQueue = new LinkedList<>();
    private List<EnemyClass> enemies = new ArrayList<>();
    private double timer = 0;

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

    // load enemies info from file to queue
    public void loadQueue(Pane layer, int levelIndex)
    {
        try (FileInputStream str = new FileInputStream("src/res/stages/Level" + levelIndex + ".txt")) {
            
            final Scanner in = new Scanner(str);
            try
            {
                for (int i = 0; i < 7; i++)
                    in.nextLine();
                final int count = in.nextInt();
                timer = in.nextDouble();
                for (int i = 0; i < count; i++)
                {
                    final String name = in.next();
                    final double time = in.nextDouble();
                    Pair<String, Double> p = new Pair<String,Double>(name, time);
                    enemiesQueue.add(p);
                }
            }
            finally
            {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // actually create enemies and spawn on the screen
    public void update(Pane layer)
    {
        if(!enemiesQueue.isEmpty()) {
            if (timer == 0) {
                final Pair<String, Double> p = enemiesQueue.poll();
                final String name = p.getKey();
                final double time = p.getValue();

                if ("Normal".equals(name)) {
                    EnemyClass e = new NormalEnemy(layer, new Image(Config.NORMAL_IMAGE), tick);
                    entities.add(e);
                    enemies.add(e);
                } else if ("Smaller".equals(name)) {
                    EnemyClass e = new SmallerEnemy(layer, new Image(Config.SMALLER_IMAGE), tick);
                    entities.add(e);
                    enemies.add(e);
                } else if ("Tanker".equals(name)) {
                    EnemyClass e = new TankerEnemy(layer, new Image(Config.TANKER_IMAGE), tick);
                    entities.add(e);
                    enemies.add(e);
                } else if ("Boss".equals(name)) {
                    EnemyClass e = new BossEnemy(layer, new Image(Config.BOSS_IMAGE), tick);
                    entities.add(e);
                    enemies.add(e);
                }
                timer = time;
            } else timer--;
        }
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

    public List<EnemyClass> getEnemies() {return enemies;}

}
