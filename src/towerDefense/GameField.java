package towerDefense;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import towerDefense.entity.enemies.*;
import towerDefense.entity.towers.*;

public class GameField {
    private final double width;
    private final double height;
    private final List<EntityClass> entities = new ArrayList<>();
    private Queue<Pair<String, Double>> enemiesQueue = new LinkedList<>();
    private List<EnemyClass> enemies = new ArrayList<>();
    private List<TowerClass> towers = new ArrayList<>();
    private double timer = 0;

    public GameField(GameStage gameStage) {
        this.width = gameStage.getWidth();
        this.height = gameStage.getWidth();
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

    public List<EnemyClass> getEnemies() {return enemies;}
    //#endregion

    // load enemies info from file to queue
    public void loadQueue(Pane layer, int levelIndex)
    {
        try (FileInputStream str = new FileInputStream("res/stages/Level" + levelIndex + ".txt")) {

            final Scanner in = new Scanner(str);
            try
            {
                // skip few first lines, which basically contains trivial things
                for (int i = 0; i < 7; i++)
                    in.nextLine();
                    
                final int count = in.nextInt();
                timer = in.nextDouble();    // initial spawn time
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

    // update state of entities list
    public void update(Pane layer)
    {
        enemies.forEach(e -> e.move());
        entities.forEach(e -> e.update());
        towers.forEach(e -> e.update(enemies));

        // a list of to be destroyed entities so we can remove all of them at once
        final List<EntityClass> destroyedEntities = new ArrayList<>();
        for (EntityClass e : entities)
        {
            if (e.getDestroyed())
            {
                destroyedEntities.add(e);
                e.removeFromLayer();
            }
        }

        entities.removeAll(destroyedEntities);
        destroyedEntities.clear();
    }

    public void addEntity(EntityClass e)
    {
        entities.add(e);
    }

    // actually spawn enemies
    public void spawnEnemies(Pane layer) {

        if (!enemiesQueue.isEmpty()) {

            if (timer == 0) {
                final Pair<String, Double> p = enemiesQueue.poll();
                final String name = p.getKey();
                final double time = p.getValue();

                if ("Normal".equals(name)) {
                    EnemyClass e = new NormalEnemy(layer, new Image(Config.NORMAL_IMAGE));
                    entities.add(e);
                    enemies.add(e);
                } else if ("Smaller".equals(name)) {
                    EnemyClass e = new SmallerEnemy(layer, new Image(Config.SMALLER_IMAGE));
                    entities.add(e);
                    enemies.add(e);
                } else if ("Tanker".equals(name)) {
                    EnemyClass e = new TankerEnemy(layer, new Image(Config.TANKER_IMAGE));
                    entities.add(e);
                    enemies.add(e);
                } else if ("Boss".equals(name)) {
                    EnemyClass e = new BossEnemy(layer, new Image(Config.BOSS_IMAGE));
                    entities.add(e);
                    enemies.add(e);
                }
                timer = time;

            } else
                timer--;

        }
    }

    // spawn towers
    public void spawnTower(Pane layer, String type, double posX, double posY)
    {
        if (type.equals("Normal"))
        {
            TowerClass t = new NormalTower(layer, this, new Image(Config.NORMAL_TOWER_IMAGE), posX, posY, Config.NORMAL_TOWER_RANGE);
            towers.add(t);
            System.out.println("Normal Tower spawned at " + posX + " " + ", " + posY);
        }
        else if (type.equals("Sniper"))
        {
            TowerClass t = new SniperTower(layer, this, new Image(Config.SNIPER_TOWER_IMAGE), posX, posY, Config.SNIPER_TOWER_RANGE);
            towers.add(t);
            System.out.println("Sniper Tower spawned at " + posX + " " + ", " + posY);
        }
        else if (type.equals("Sniper"))
        {
            TowerClass t = new MachineGunTower(layer, this, new Image(Config.MACHINE_TOWER_IMAGE), posX, posY, Config.MACHINE_TOWER_RANGE);
            towers.add(t);
            System.out.println("Machine Gun Tower spawned at " + posX + " " + ", " + posY);
        }
    }
}
