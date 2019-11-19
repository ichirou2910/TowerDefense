package towerDefense.entity.towers;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

import java.util.List;

import static towerDefense.Config.TILE_SIZE;

public abstract class TowerClass extends EntityClass {
    private GameField gf;
    private EnemyClass target;
    private GameLog log;
    private Player player;

    private ImageView menuView;

    private String type;
    private double range;
    private int damage;
    private final int price;
    private int level;

    private boolean onSelected = false;
    private double menuWidth;
    private double menuHeight;

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

        menuView = new ImageView(new Image("file:res/images/enemies/Boss.png"));
        menuView.relocate(this.getPosX(), this.getPosY());
        menuWidth = menuView.getImage().getWidth();
        menuHeight = menuView.getImage().getHeight();


        this.getLayer().getChildren().add(menuView);

        addListener();
        menuView.setVisible(false);
        count++;
        System.out.println("Tower #" + count);
    }

    //Getter
    public double getRange() {return this.range;}
    public double getSpeed() {return 0.0;}
    public int getDamage() {return this.damage;}
    public void setDamage(int damage) {this.damage = damage;}
    public int getPrice() {return this.price;}

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
                MoveTo start = new MoveTo(posX + TILE_SIZE, posY + TILE_SIZE);
                LineTo ln = new LineTo(e.getMidX() + TILE_SIZE, e.getMidY() + TILE_SIZE);
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
                    if(e.getHealth() <= 0) {
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

    // TODO: move tower upgrade listener to Controller
    // TODO: design menu image, currently using a random one for testing
    // TODO: restrict to 1 menu to trigger only, when a tower is triggering menu, other cannot trigger
    public void addListener()
    {
        this.getImageView().setOnMousePressed(event -> {
            if (!onSelected) {
                System.out.println("Selected");
                onSelected = true;
            }
        });

        menuView.setOnMousePressed(event -> {
            if (onSelected && menuView.isVisible()) {
                double x = event.getX();
                double y = event.getY();

                if (x < menuWidth / 2 && y < menuHeight / 2) {
                    System.out.println("Top left corner");
                    this.towerUpgrade();
                }
                else if (x < menuWidth / 2 && y > menuHeight / 2) {
                    System.out.println("Bottom left corner");
                }
                else if (x > menuWidth / 2 && y < menuHeight / 2) {
                    System.out.println("Top right corner");
                }
                else if (x > menuWidth / 2 && y > menuHeight / 2) {
                    System.out.println("Bottom right corner");
                }

                onSelected = false;
            }
        });
    }

    private void towerUpgrade()
    {
        if (level < Config.TOWER_MAX_LEVEL) {
            final int upgradePrice = price * 2 / 3;
            if (player.getMoney() >= upgradePrice) {
                damage *= 2;
                range *= 1.2;
                log.addMessage("> " + type + " upgraded. Damage x2, Range x1.2. Spent $" + upgradePrice);
                level++;
                player.setMoney(player.getMoney() - upgradePrice);
            } else {
                log.addMessage("> Not enough money to upgrade. Need $" + upgradePrice);
            }
        }
        else
            log.addMessage("Tower reached max level!");
    }

    private void updateMenu() {

        menuView.relocate(this.getPosX(), this.getPosY());
        if (onSelected)
            menuView.setVisible(true);
        else menuView.setVisible(false);
    }
}
