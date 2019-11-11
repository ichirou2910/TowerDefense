package towerDefense.entity.towers;

import towerDefense.Config;
import towerDefense.EntityClass;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.entity.enemies.EnemyClass;
import towerDefense.ui.AngleNormalize;

import java.util.List;

public abstract class TowerClass extends EntityClass {
    private EnemyClass target;

    private final double range;
    private final double speed;
    private final int damage;
    private final double rotationEasing = 10; //higher value rotate slower
    private final double firingRangeLimitDeg = 20;

    private boolean withinFiringRange = false;

    protected TowerClass(Pane layer, Image image, long tick, double range, double speed, int damage) {
        super(layer, image, tick, 3*46, 5*46, 90);
        this.range = range;
        this.speed = speed;
        this.damage = damage;
    }

    //Getter
    public double getRange() {return this.range;}
    public double getSpeed() {return this.speed;}
    public int getDamage() {return this.damage;}

    public void move() {
        TowerClass track = this;

        //reset within firing range
        withinFiringRange = false;

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
            dif = AngleNormalize.normalRelativeAngleDeg(dif);

            //add the dif to the current angle, while easing the rotation when target comes closer
            currentAngle = currentAngle + dif/rotationEasing;

            //apply rotation
            setRotation(currentAngle);

            //determines if the target is within firing range
            withinFiringRange = Math.abs(dif) < firingRangeLimitDeg;
        }
    }

    public void checkTarget() {
        if(target == null)
        {
            return;
        }

        if(!target.isAlive()) {
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
            if(!cTarget.isAlive()) continue;

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

    public void setTarget(EnemyClass e) {
        this.target = e;
    }
    public EnemyClass getTarget() { return this.target; }
}
