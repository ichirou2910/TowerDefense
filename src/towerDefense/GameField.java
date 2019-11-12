package towerDefense;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import javafx.util.Pair;
import towerDefense.entity.EffectClass;
import towerDefense.entity.bullets.BulletClass;
import towerDefense.entity.bullets.SpawnBullet;
import towerDefense.entity.enemies.*;

public class GameField {
    private final double width;
    private final double height;
    private final List<EntityClass> entities = new ArrayList<>();
    private Queue<Pair<String, Double>> enemiesQueue = new LinkedList<>();
    private List<EnemyClass> enemies = new ArrayList<>();
    private double timer = 0;
    private double bTimer = 0;

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

    //Spawn and shoot
    public void shoot(Pane layer, double posX, double posY, double rotation, EnemyClass e, String type) {
        if(e != null) {
            if (bTimer == 0) {

                //spawn bullet here
                SpawnBullet s = new SpawnBullet();
                BulletClass b = s.createBullet(layer, rotation, type);

                //Bullet trace
                entities.add(b);
                Path p = new Path();
                MoveTo start = new MoveTo(posX, posY);
                LineTo ln = new LineTo(e.getMidX(), e.getMidY());
                p.getElements().add(start);
                p.getElements().add(ln);

                PathTransition pT = new PathTransition();
                pT.setDuration(Duration.millis(b.getSpeed()));
                pT.setNode(b.getImageView());
                pT.setPath(p);
                pT.setAutoReverse(false);
                pT.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pT.setOnFinished(ev -> {
                    //Bullet impact
                    EntityClass ex = new EffectClass(layer, new Image(Config.IMPACT_BULLET_IMAGE), e.getPosX(), e.getPosY(), rotation + 180);
                    FadeTransition ft = new FadeTransition(Duration.millis(200), ex.getImageView());
                    ft.setFromValue(1.0);
                    ft.setToValue(0.0);
                    ft.setAutoReverse(false);
                    ft.play();

                    //Bullet set to destroyed, also reduce enemy's HP
                    b.setDestroyed(true);
                    e.setHealth(e.getHealth() - b.getDamage());
                    bTimer = b.getRateOfFire();

                    //Explosion when enemy dies
                    if(!e.isAlive()) {
                        ex = new EffectClass(layer, new Image(Config.EXPLOSION3), e.getPosX() - 5, e.getPosY() - 5, 0);
                        ft = new FadeTransition(Duration.millis(500), ex.getImageView());
                        ft.setFromValue(1.0);
                        ft.setToValue(0.0);
                        ft.setAutoReverse(false);
                        ft.play();
                    }
                });
                pT.play();
            }
            bTimer--;
        }
    }
}
