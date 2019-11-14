package towerDefense.entity.towers;

import java.util.List;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.util.Duration;

import towerDefense.Config;
import towerDefense.EntityClass;
import towerDefense.GameField;
import towerDefense.entity.EffectClass;
import towerDefense.entity.bullets.BulletClass;
import towerDefense.entity.bullets.SpawnBullet;
import towerDefense.entity.enemies.EnemyClass;

public abstract class TowerClass extends EntityClass {
    private GameField gf;
    private EnemyClass target;

    private final double range;
    private final int damage;

    private double bTimer = 0;

    protected TowerClass(Pane layer, GameField gf, Image image, double posX, double posY, double range, int damage) {
        super(layer, image, posX, posY, 0);
        this.range = range;
        this.damage = damage;
        this.gf = gf;
    }

    //Getter
    public double getRange() {return this.range;}
    public double getSpeed() {return 0.0;}
    public int getDamage() {return this.damage;}

    public void update(List<EnemyClass> e)
    {
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

            //normalize
            // dif = AngleNormalize.normalRelativeAngleDeg(dif);

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
                BulletClass b = s.createBullet(layer, rotation, type);

                //Bullet trace
                gf.addEntity(b);
                Path p = new Path();
                MoveTo start = new MoveTo(posX + 46, posY + 46);
                LineTo ln = new LineTo(e.getMidX() + 46, e.getMidY() + 46);
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
}
