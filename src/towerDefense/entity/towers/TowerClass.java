package towerDefense.entity.towers;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import towerDefense.Config;
import towerDefense.EntityClass;
import towerDefense.GameField;
import towerDefense.Player;
import towerDefense.entity.EffectClass;
import towerDefense.entity.bullets.BulletClass;
import towerDefense.entity.bullets.SpawnBullet;
import towerDefense.entity.enemies.EnemyClass;
import towerDefense.ui.GameLog;
import towerDefense.utilities.Sprite;

import java.util.List;

public abstract class TowerClass extends EntityClass {
    private GameField gf;
    private EnemyClass target;
    private GameLog log;
    private Player player;

    private Sprite menu;
    private Sprite upgrade;
    private Sprite sell;
    private Image normal2, normal3;
    private Image sniper2, sniper3;
    private Image machine2, machine3;

    private String type;
    private double range;
    private int damage;
    private final int price;
    private int level;

    private boolean onSelected = false;
    private int flagTrig = 0;

    private double bTimer = 0;
    public static int count = 0;

    protected TowerClass(Pane layer, GameField gf, GameLog log, Image image, String type, double posX, double posY, double range, int damage, int price, Player p) {
        super(layer, image, posX, posY, 0);
        this.type = type;
        this.log = log;
        this.range = range;
        this.damage = damage;
        this.gf = gf;
        this.price = price;
        this.level = 1;
        this.player = p;

        normal2 = new Image(Config.NORMAL_TOWER2_IMAGE);
        normal3 = new Image(Config.NORMAL_TOWER3_IMAGE);
        sniper2 = new Image(Config.SNIPER_TOWER2_IMAGE);
        sniper3 = new Image(Config.SNIPER_TOWER3_IMAGE);
        machine2 = new Image(Config.MACHINE_TOWER2_IMAGE);
        machine3 = new Image(Config.MACHINE_TOWER3_IMAGE);

        menu = new Sprite(new Image(Config.MENU_BLANK), this.getMidX(), this.getMidY(), true);
        upgrade = new Sprite(new Image(Config.UPGRADE_IMAGE), this.getMidX() - 45, this.getMidY() - 30, false);
        sell = new Sprite(new Image(Config.SELL_IMAGE), this.getMidX() + 15, this.getMidY() - 30, false);
        this.getLayer().getChildren().addAll(menu.getImageView(), upgrade.getImageView(), sell.getImageView());

        menu.getImageView().setOpacity(0.01);
        upgrade.getImageView().setVisible(false);
        sell.getImageView().setVisible(false);

        addListener();
        count++;
        System.out.println("Tower #" + count);
    }

    //Getter
    public double getSpeed() {return 0.0;}
    public int getPrice() {return this.price;}
    public int getFlagTrig() {return this.flagTrig;}

    public void update(List<EnemyClass> e)
    {
        updateMenu();
        checkTarget();
        findTarget(e);
        move();
        super.update();
        shoot(getLayer(), getMidX(), getMidY(), getRotation(), getTarget(), getType());
    }

    public void setTarget(EnemyClass e) {
        this.target = e;
    }
    public EnemyClass getTarget() { return this.target; }
    public abstract String getType();

    public void move() {
        TowerClass track = this;

        //rotate towards target
        if(target != null) {
            //get target angle
            double xDis = target.getMidX() - track.getMidX();
            double yDis = target.getMidY() - track.getMidY();
            double targetAngle = Math.atan2(yDis, xDis) + Math.PI/2;
            //convert to degree
            targetAngle = Math.toDegrees(targetAngle);

            double currentAngle = track.getRotation();
            //calculate difference between target and tower
            double dif = targetAngle - currentAngle;

            //add the dif to the current angle, while easing the rotation when target comes closer
            currentAngle = currentAngle + dif;

            //apply rotation
            setRotation(currentAngle);
        }
    }

    public void checkTarget() {
        if(target == null)
        {
            return;
        }

        if(target.getDestroyed()) {
            setTarget(null);
            return;
        }

        // get distance between tower and target
        double disX = target.getMidX() - this.getMidX();
        double disY = target.getMidY() - this.getMidY();
        double disTotal = Math.sqrt(disX*disX + disY*disY);

        if(Double.compare(disTotal, range) > 0) setTarget(null);
    }

    public void findTarget(List<EnemyClass> e) {
        if(getTarget() != null) {
            return;
        }
        EnemyClass closetTarget = null;
        double closetDistance = 0.0;

        for(EnemyClass cTarget: e) {
            if(cTarget.getDestroyed()) continue;

            // get distance between tower and target
            double disX = cTarget.getMidX() - this.getMidX();
            double disY = cTarget.getMidY() - this.getMidY();
            double disTotal = Math.sqrt(disX*disX + disY*disY);

            // check if enemy is within range
            if(Double.compare(disTotal, range) > 0) continue;

            if(closetTarget == null) {
                closetTarget = cTarget;
                closetDistance = disTotal;
            } else if (Double.compare(closetDistance, disTotal) > 0) {
                closetTarget = cTarget;
                closetDistance = disTotal;
            }
        }

        setTarget(closetTarget);
    }

    public void shoot(Pane layer, double posX, double posY, double rotation, EnemyClass e, String type) {
        if(e != null) {
            if (bTimer == 0) {

                //spawn bullet here
                SpawnBullet s = new SpawnBullet();
                BulletClass b = s.createBullet(layer, rotation, type, damage);

                //Bullet trace
                gf.addEntity(b);
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
                    EntityClass ex = new EffectClass(layer, new Image(Config.IMPACT_BULLET_IMAGE), e.getMidX(), e.getMidY(), rotation + 180);
                    FadeTransition ft = new FadeTransition(Duration.millis(200), ex.getImageView());
                    ft.setFromValue(1.0);
                    ft.setToValue(0.0);
                    ft.setAutoReverse(false);
                    ft.play();

                    //Bullet set to destroyed, also reduce enemy's HP
                    b.setDestroyed(true);
                    e.setHealth(e.getHealth() - b.getDamage() * (1 - e.getArmor()));
                    bTimer = b.getRateOfFire();
                });
                pT.play();
            }
            bTimer--;
        }
    }

    public void addListener()
    {
        this.getImageView().setOnMousePressed(event -> {
            if (!onSelected) {
                System.out.println("Selected");
                onSelected = true;
                flagTrig = 1;
            }
        });

        upgrade.getImageView().setOnMousePressed(event -> {
            if (onSelected && upgrade.getImageView().isVisible()) {
                this.towerUpgrade();
                onSelected = false;
                flagTrig = 0;
            }
        });

        sell.getImageView().setOnMousePressed(event -> {
            if (onSelected && sell.getImageView().isVisible()) {
                this.towerSell();
                onSelected = false;
                flagTrig = 0;
            }
        });

        menu.getImageView().setOnMouseEntered(event -> {
            onSelected = false;
        });
    }

    private void towerUpgrade() {
        if (level < Config.TOWER_MAX_LEVEL) {
            final int upgradePrice = price * 3 / 5;

            if (player.getMoney() >= upgradePrice) {

                //replace with upgraded image and change stats
                if(this.type.equals("Normal Tower")) {
                    if(level == 1)
                        this.getSprite().setImageView(normal2);
                    else
                        this.getSprite().setImageView(normal3);

                    damage += 2;
                    range *= 1.1;
                    log.addMessage("> " + type + " upgraded. Damage +2, Range x1.1. Spent $" + upgradePrice);
                }

                if(this.type.equals("Sniper Tower")) {
                    if(level == 1)
                        this.getSprite().setImageView(sniper2);
                    else
                        this.getSprite().setImageView(sniper3);

                    damage += 5;
                    range *= 1.2;
                    log.addMessage("> " + type + " upgraded. Damage +5, Range x1.2. Spent $" + upgradePrice);
                }

                if(this.type.equals("Machine Gun Tower")) {
                    if(level == 1)
                        this.getSprite().setImageView(machine2);
                    else
                        this.getSprite().setImageView(machine3);

                    damage += 1;
                    range *= 1.2;
                    log.addMessage("> " + type + " upgraded. Damage +1, Range x1.2. Spent $" + upgradePrice);
                }

                //level up and subtract current money
                level++;
                player.setMoney(player.getMoney() - upgradePrice);
            } else {
                log.addMessage("> Not enough money to upgrade. Need $" + upgradePrice);
            }
        }
        else
            log.addMessage("Tower reached max level!");
    }

    private void towerSell() {
        this.setDestroyed(true);
        System.out.println("Destroyed: " + this.getDestroyed());
        int sellPrice = price * 2 / 3;
        player.setMoney(sellPrice + player.getMoney());
        log.addMessage("> " + type + " sold. Get $" + sellPrice);
    }

    private void updateMenu() {

        if (onSelected) {
            menu.setVisible(true);
            upgrade.getImageView().setVisible(true);
            sell.getImageView().setVisible(true);
        }
        else {
            menu.setVisible(false);
            upgrade.getImageView().setVisible(false);
            sell.getImageView().setVisible(false);
        }
    }

    public void deleteMenu() {
        onSelected = false;
        flagTrig = 0;
    }
}
