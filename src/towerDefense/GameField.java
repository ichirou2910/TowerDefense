package towerDefense;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import javafx.util.Pair;
import towerDefense.entity.EffectClass;
import towerDefense.entity.enemies.*;
import towerDefense.entity.towers.TowerClass;
import towerDefense.ui.GameLog;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class GameField {

    private final List<EntityClass> entities = new ArrayList<>();
    private Queue<Pair<String, Double>> enemiesQueue = new LinkedList<>();

    private List<EnemyClass> enemies = new ArrayList<>();
    private List<TowerClass> towers = new ArrayList<>();
    private double timer = 0;
    private int counter = 0;
    private int wave;
    private int waveNum;
    private int levelIndex;
    private int enemiesDestroyed = 0;
    private boolean stageSet = false;
    private boolean healthEstablished = true;
    private boolean baseBuilt = false;
    private boolean confirmed = true;
    private TowerClass tow;
    private int x = 915, y = 595;

    private ImageView end = new ImageView(new Image("file:res/images/GameOver.png"));
    private ImageView vic = new ImageView(new Image("file:res/images/Victory.png"));

    ImageView base;
    ImageView health;

    public GameField(GameStage gameStage) {
        entities.addAll(gameStage.getEntities());
        base = new ImageView(new Image(Config.BASE_IMAGE));
        health = new ImageView(new Image(Config.HIGH));
        wave = gameStage.getWave();
        levelIndex = 1;
    }

    // Getters
    public List<EnemyClass> getEnemies() {return enemies;}
    public List<TowerClass> getTowers() {return towers;}

    // load enemies info from file to queue
    public void loadQueue(Pane layer, Player player, GameLog gameLog)
    {
        if(!stageSet && levelIndex <= wave) {
            System.out.println("Stage " + levelIndex);
            gameLog.addMessage("> Stage " + levelIndex + " begin!");
            try (FileInputStream str = new FileInputStream("res/stages/Level" + levelIndex + ".txt")) {

                final Scanner in = new Scanner(str);

                try {
                    // skip few first lines, which basically contains trivial things
                    for (int i = 0; i < 7; i++)
                        in.nextLine();

                    final int balance = in.nextInt();
                    player.setMoney(player.getMoney() + balance);

                    waveNum = in.nextInt();
                    timer = in.nextDouble();    // initial spawn time
                    for (int i = 0; i < waveNum; i++) {
                        final String name = in.next();
                        final double time = in.nextDouble();
                        Pair<String, Double> p = new Pair<String, Double>(name, time);
                        enemiesQueue.add(p);
                    }
                } finally {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            stageSet = true;
        }
    }

    // update state of entities list
    public void update(Pane layer, Player p)
    {
        for(TowerClass t : towers)
            counter += t.getFlagTrig();

        for(TowerClass t : towers)
        {
            if(counter == 1) {
                if (t.getFlagTrig() == 1) {
                    tow = t;
                }
            }
        }

        for(TowerClass t : towers)
        {
            if(counter == 2) {
                if (t.equals(tow)) {
                    t.deleteMenu();
                }
            }
        }

        counter = 0;

        enemies.forEach(e -> e.move());
        entities.forEach(e -> e.update());
        towers.forEach(e -> e.update(enemies));

        // a list of to be destroyed entities so we can remove all of them at once
        final List<EntityClass> destroyedEntities = new ArrayList<>();
        for (EntityClass e : entities)
        {
            if (e.getDestroyed())
            {
                if (e instanceof EnemyClass) {
                    p.takeReward(((EnemyClass) e).getReward(), ((EnemyClass) e).getType());
                    ((EnemyClass) e).setReward(0);
                    destroyedEntities.add(e);
                    enemiesDestroyed++;
                    System.out.println(enemiesDestroyed);

                    //Explosion effect
                    EntityClass ex = new EffectClass(layer, new Image(Config.EXPLOSION3), e.getMidX(), e.getMidY(), 0);
                    FadeTransition ft = new FadeTransition(Duration.millis(500), ex.getImageView());
                    ft.setFromValue(1.0);
                    ft.setToValue(0.0);
                    ft.setAutoReverse(false);
                    ft.play();

                    //Explosion audio
                    AudioClip explosion = new AudioClip("file:res/Sound/Explosion.mp3");
                    explosion.setVolume(0.5);
                    explosion.play();
                }
                else
                    destroyedEntities.add(e);
                e.removeFromLayer();
            }
        }

        for (EntityClass e : towers)
        {
            if (e.getDestroyed())
            {
                destroyedEntities.add(e);
                e.removeFromLayer();
            }
        }

        entities.removeAll(destroyedEntities);
        towers.removeAll(destroyedEntities);
        enemies.removeAll(destroyedEntities);
        destroyedEntities.clear();
    }

    public void addEntity(EntityClass e)
    {
        entities.add(e);
    }

    // actually spawn enemies
    public void spawnEnemies(Pane layer) {

        if (!enemiesQueue.isEmpty() && confirmed) {

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

    public void buildBase(Pane layer, Player p) {
        //Base built
        if(!baseBuilt) {
            base.relocate(x, y);
            layer.getChildren().add(base);
            baseBuilt = true;
        }

        //Health bar
        if(p.getHealth() == 80) {
            healthEstablished = true;
            health = new ImageView(new Image(Config.MED_HIGH));
        } else if(p.getHealth() == 60) {
            healthEstablished = true;
            health = new ImageView(new Image(Config.MED));
        } else if(p.getHealth() == 40) {
            healthEstablished = true;
            health = new ImageView(new Image(Config.LOW_MED));
        } else if(p.getHealth() == 20){
            healthEstablished = true;
            health = new ImageView(new Image(Config.LOW));
        }

        if(healthEstablished) {
            health.relocate(904, 510);
            layer.getChildren().add(health);
            healthEstablished = false;
        }

        for(EnemyClass e : enemies)
        {
            if(e.getMidX() - 23 >= x && e.getMidY() - 23 >= y)
            {
                p.setHealth(p.getHealth() - 10);
                e.setReward(0);
                e.setDestroyed(true);
                EntityClass ex = new EffectClass(layer, new Image(Config.EXPLOSION3), e.getMidX() - 25, e.getMidY(), 0);
                FadeTransition ft = new FadeTransition(Duration.millis(500), ex.getImageView());
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setAutoReverse(false);
                ft.play();
            }
        }
        if(confirmed)
            base.toFront();
    }

    public void gameOver(Pane layer, Player p) {
        if(confirmed) {
            if(p.getHealth() == 0) {
                layer.getChildren().add(end);
                for(EntityClass e : entities) {
                    e.setDestroyed(true);
                }
                confirmed = false;
            }
        }
    }

    public void waveOver(GameLog gameLog) {
        if(enemiesDestroyed == waveNum) {
            enemiesDestroyed = 0;
            stageSet = false;
            gameLog.addMessage("> Stage " + levelIndex + " cleared!");
            levelIndex++;
            System.out.println(levelIndex);
        }
    }

    public void victory(Pane layer) {
        if((levelIndex > wave) && confirmed) {
            layer.getChildren().add(vic);
            vic.relocate(0, -920);
            Path p = new Path();
            MoveTo start = new MoveTo(500, 0);
            LineTo ln = new LineTo(500, 1100);
            p.getElements().addAll(start, ln);

            PathTransition pt = new PathTransition();
            pt.setDuration(Duration.millis(3000));
            pt.setPath(p);
            pt.setNode(vic);
            pt.play();

            confirmed = false;
        }
    }
}
